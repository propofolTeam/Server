package com.hackerton.propofol.service;

import com.hackerton.propofol.dto.PostListResponse;
import com.hackerton.propofol.dto.PostWriteRequest;
import org.springframework.data.domain.Pageable;

public interface PostService {

    void write(PostWriteRequest postWriteRequest);
    PostListResponse getList(Pageable pageable);
}
