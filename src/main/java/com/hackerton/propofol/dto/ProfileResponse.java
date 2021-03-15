package com.hackerton.propofol.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProfileResponse {

    private String email;

    private String name;

    private String image;

    private int totalElements;

    private int totalPage;

    private List posts;
}
