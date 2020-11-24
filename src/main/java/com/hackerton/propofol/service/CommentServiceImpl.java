package com.hackerton.propofol.service;

import com.hackerton.propofol.domain.Comment;
import com.hackerton.propofol.domain.User;
import com.hackerton.propofol.domain.repository.CommentRepository;
import com.hackerton.propofol.domain.repository.PostRepository;
import com.hackerton.propofol.domain.repository.UserRepository;
import com.hackerton.propofol.dto.CommentWriteRequest;
import com.hackerton.propofol.exception.PostNotFoundException;
import com.hackerton.propofol.exception.UserNotFoundException;
import com.hackerton.propofol.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void write(Long postId, CommentWriteRequest commentWriteRequest) {
        User user = userRepository.findByEmail(authenticationFacade.getUserEmail())
                .orElseThrow(UserNotFoundException::new);

        postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        commentRepository.save(
                Comment.builder()
                        .postId(postId)
                        .userId(user.getId())
                        .comment(commentWriteRequest.getContent())
                        .time(LocalDateTime.now())
                        .build()
        );
    }
}
