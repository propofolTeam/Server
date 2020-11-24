package com.hackerton.propofol.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {

    private Long id;

    private String comment;

    private String writer;

    private String image;
}
