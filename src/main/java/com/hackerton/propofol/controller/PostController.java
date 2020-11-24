package com.hackerton.propofol.controller;

import com.hackerton.propofol.dto.PostListResponse;
import com.hackerton.propofol.dto.PostWriteRequest;
import com.hackerton.propofol.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public void write(@ModelAttribute @Valid PostWriteRequest postWriteRequest) {
        postService.write(postWriteRequest);
    }

    @GetMapping
    public PostListResponse getList(Pageable pageable) {
        return postService.getList(pageable);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable @Valid Long postId) {
        postService.deletePost(postId);
    }
}
