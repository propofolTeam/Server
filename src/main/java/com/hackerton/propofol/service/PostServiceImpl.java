package com.hackerton.propofol.service;

import com.hackerton.propofol.domain.Post;
import com.hackerton.propofol.domain.User;
import com.hackerton.propofol.domain.repository.CommentRepository;
import com.hackerton.propofol.domain.repository.PostRepository;
import com.hackerton.propofol.domain.repository.UserRepository;
import com.hackerton.propofol.dto.PostContentResponse;
import com.hackerton.propofol.dto.PostListResponse;
import com.hackerton.propofol.dto.PostResponse;
import com.hackerton.propofol.dto.PostWriteRequest;
import com.hackerton.propofol.exception.PostNotFoundException;
import com.hackerton.propofol.exception.UserNotFoundException;
import com.hackerton.propofol.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final AuthenticationFacade authenticationFacade;

    @Value("${file.upload.dir}")
    private String fileDirPath;

    @SneakyThrows
    @Override
    public void write(PostWriteRequest postWriteRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String time = sdf.format(date);

        Post post = postRepository.save(
                Post.builder()
                        .title(postWriteRequest.getTitle())
                        .content(postWriteRequest.getContent())
                        .userId(user.getId())
                        .createdAt(time)
                        .build()
        );

        if(postWriteRequest.getFile() != null) {
            String fileId = UUID.randomUUID().toString();

            String path = Paths.get(fileDirPath, fileId).toString();

            postRepository.save(post.updateFile(fileId, postWriteRequest.getFile().getOriginalFilename()));

            postWriteRequest.getFile().transferTo(new File(path));
        }
    }

    @Override
    public PostListResponse getList(Pageable pageable) {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Page<Post> postPage = postRepository.findAllBy(pageable);

        List<PostResponse> postResponses = new ArrayList<>();

        for (Post post : postPage) {
            User user = userRepository.findById(post.getUserId())
                    .orElseThrow(UserNotFoundException::new);

            postResponses.add(
                    PostResponse.builder()
                            .id(post.getId())
                            .title(post.getTitle())
                            .writer(user.getName())
                            .userId(user.getId())
                            .image(user.getImage())
                            .commentCount(commentRepository.countByPostId(post.getId()))
                            .createdAt(post.getCreatedAt())
                            .build()
            );
        }

        return PostListResponse.builder()
                .totalElements((int) postPage.getTotalElements())
                .totalPage(postPage.getTotalPages())
                .response(postResponses)
                .build();
    }

    @Override
    public PostContentResponse getContent(Long postId) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        User writer = userRepository.findById(post.getUserId())
                .orElseThrow(UserNotFoundException::new);

        return PostContentResponse.builder()
                .id(postId)
                .title(post.getTitle())
                .content(post.getContent())
                .writer(writer.getName())
                .image(writer.getImage())
                .createdAt(post.getCreatedAt())
                .isMine(writer.equals(user))
                .fileId(post.getFileId())
                .fileName(post.getFileName())
                .build();
    }

    @Override
    public void deletePost(Long postId) {
        userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        postRepository.deleteById(postId);
    }
}
