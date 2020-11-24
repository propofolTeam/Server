package com.hackerton.propofol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostListResponse {

    private int totalElements;

    private int totalPage;

    @JsonProperty("response")
    private List response;
}
