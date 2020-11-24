package com.hackerton.propofol.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProfileUpdateRequest {

    private String name;

    private MultipartFile image;
}
