package com.hackerton.propofol.controller;

import com.hackerton.propofol.dto.PostContentResponse;
import com.hackerton.propofol.dto.PostListResponse;
import com.hackerton.propofol.dto.PostWriteRequest;
import com.hackerton.propofol.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public PostListResponse getList(@PageableDefault(size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
        return postService.getList(pageable);
    }

    @GetMapping("/{postId}")
    public PostContentResponse getContent(@PathVariable @Valid Long postId) {
        return  postService.getContent(postId);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable @Valid Long postId) {
        postService.deletePost(postId);
    }
}
