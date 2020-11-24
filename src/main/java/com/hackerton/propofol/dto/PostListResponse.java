package com.hackerton.propofol.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostListResponse {

    private int totalElements;

    private int totalPage;

    private List response;
}
