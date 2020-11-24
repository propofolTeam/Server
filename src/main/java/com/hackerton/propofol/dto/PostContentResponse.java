package com.hackerton.propofol.dto;

import com.hackerton.propofol.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

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

    private List comments;
}
