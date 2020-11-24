package com.hackerton.propofol.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponse {

    private Long id;

    private String title;

    private String writer;

    private String image;

    private Integer commentCount;
}
