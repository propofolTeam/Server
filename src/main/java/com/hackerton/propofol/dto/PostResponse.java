package com.hackerton.propofol.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

    private Long id;

    private String title;

    private String writer;

    private Long userId;

    private String image;

    private String createdAt;

    private Integer commentCount;
}
