package com.hackerton.propofol.service;

import com.hackerton.propofol.dto.PostContentResponse;
import com.hackerton.propofol.dto.PostListResponse;
import com.hackerton.propofol.dto.PostUpdateRequest;
import com.hackerton.propofol.dto.PostWriteRequest;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PostService {

    void write(PostWriteRequest postWriteRequest);
    PostListResponse getList(Pageable pageable);
    PostContentResponse getContent(Long postId);
    ResponseEntity<Resource> downloadFile(Long postId, String fileId);
    void updatePost(Long postId, PostUpdateRequest postUpdateRequest);
    void deletePost(Long postId);
}
