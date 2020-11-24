package com.hackerton.propofol.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostContentResponse {

    private Long id;

    private String title;

    private String content;

    private String writer;

    private String createdAt;

    private String image;

    private boolean isMine;

    private String fileId;

    private String fileName;
}
