package com.hackerton.propofol.service;

import com.hackerton.propofol.dto.CommentWriteRequest;

public interface CommentService {

    void write(Long postId, CommentWriteRequest commentWriteRequest);
}
