package com.hackerton.propofol.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentWriteRequest {

    @NotBlank
    private String comment;
}
