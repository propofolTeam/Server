package com.hackerton.propofol.controller;

import  com.hackerton.propofol.dto.CommentWriteRequest;
import com.hackerton.propofol.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public void write(@PathVariable @Valid Long postId,
                      @RequestBody @Valid CommentWriteRequest commentWriteRequest) {
        commentService.write(postId, commentWriteRequest);
    }
}
