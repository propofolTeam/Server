package com.hackerton.propofol.service;

import com.hackerton.propofol.domain.Post;
import com.hackerton.propofol.domain.User;
import com.hackerton.propofol.domain.repository.CommentRepository;
import com.hackerton.propofol.domain.repository.PostRepository;
import com.hackerton.propofol.domain.repository.UserRepository;
import com.hackerton.propofol.dto.*;
import com.hackerton.propofol.exception.UserDuplicateException;
import com.hackerton.propofol.exception.UserNotFoundException;
import com.hackerton.propofol.security.AuthenticationFacade;
import com.hackerton.propofol.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final ImageService imageService;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationFacade authenticationFacade;

    private final PasswordEncoder passwordEncoder;

    @Value("${image.upload.dir}")
    private String imageDirPath;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    @SneakyThrows
    @Override
    public void join(JoinRequest joinRequest) {
        userRepository.findByEmail(joinRequest.getEmail()).ifPresent(user -> {
            throw new UserDuplicateException();
        });

        String password = passwordEncoder.encode(joinRequest.getPassword());

        String imageName = UUID.randomUUID().toString();

        userRepository.save(
                User.builder()
                        .email(joinRequest.getEmail())
                        .password(password)
                        .name(joinRequest.getName())
                        .image(imageName)
                        .build()
        );

        joinRequest.getImage().transferTo(new File(imageDirPath, imageName));
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();

        userRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(loginRequest.getPassword(), u.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        return TokenResponse.builder()
                .accessToken(jwtTokenProvider.generateAccessToken(email))
                .refreshToken(jwtTokenProvider.generateRefreshToken(email))
                .tokenType(prefix)
                .build();
    }

    @Override
    public ProfileResponse getProfile(Long userId, Pageable pageable) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        User profile = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        Page<Post> postPage = postRepository.findAllByUserId(pageable, profile.getId());

        List<PostResponse> posts = new ArrayList<>();

        for (Post post : postPage) {
            posts.add(
                    PostResponse.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .writer(user.getName())
                            .image(user.getImage())
                            .commentCount(commentRepository.countByPostId(post.getId()))
                            .build()
            );
        }

        return ProfileResponse.builder()
                .email(profile.getEmail())
                .name(profile.getName())
                .image(profile.getImage())
                .isMine(profile.equals(user))
                .totalElements((int) postPage.getTotalElements())
                .totalPage(postPage.getTotalPages())
                .posts(posts)
                .build();
    }

    @SneakyThrows
    @Override
    public void updateProfile(ProfileUpdateRequest profileUpdateRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        if(profileUpdateRequest.getName() != null) {
            userRepository.save(user.updateName(profileUpdateRequest.getName()));
        }

        if(profileUpdateRequest.getImage() != null) {
            new File(imageDirPath, user.getImage()).deleteOnExit();

            String imageName = UUID.randomUUID().toString();

            userRepository.save(user.updateImage(imageName));

            profileUpdateRequest.getImage().transferTo(new File(imageDirPath, imageName));
        }
    }
}
