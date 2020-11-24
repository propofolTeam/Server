package com.hackerton.propofol.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class LoginRequest {

    @Email
    private String email;

    @NotBlank
    private String password;
}
