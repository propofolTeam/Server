package com.hackerton.propofol.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostWriteRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private MultipartFile file;
}
