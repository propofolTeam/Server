package com.hackerton.propofol.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostUpdateRequest {

    private String title;

    private String content;

    private MultipartFile file;
}
