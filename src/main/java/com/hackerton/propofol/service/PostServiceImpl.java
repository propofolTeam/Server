package com.hackerton.propofol.service;

import com.hackerton.propofol.domain.Post;
import com.hackerton.propofol.domain.User;
import com.hackerton.propofol.domain.repository.PostRepository;
import com.hackerton.propofol.domain.repository.UserRepository;
import com.hackerton.propofol.dto.PostWriteRequest;
import com.hackerton.propofol.exception.UserNotFoundException;
import com.hackerton.propofol.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final AuthenticationFacade authenticationFacade;

    @Value("${file.upload.dir}")
    private String fileDirPath;

    @SneakyThrows
    @Override
    public void write(PostWriteRequest postWriteRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.save(
                Post.builder()
                        .title(postWriteRequest.getTitle())
                        .content(postWriteRequest.getContent())
                        .userId(user.getId())
                        .time(LocalDateTime.now())
                        .build()
        );

        if(postWriteRequest.getFile() != null) {
            String filePath = postWriteRequest.getFile().getOriginalFilename();

            postWriteRequest.getFile().transferTo(new File(fileDirPath, filePath));

            postRepository.save(post.updateFile(filePath));
        }
    }
}
