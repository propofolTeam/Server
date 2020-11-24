package com.hackerton.propofol.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponse {

    private String email;

    private String name;

    private String image;

    private boolean isMine;

    private int totalElements;

    private int totalPage;

    private String[] posts;
}
