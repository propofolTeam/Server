package com.hackerton.propofol.service;

import com.hackerton.propofol.dto.JoinRequest;
import com.hackerton.propofol.dto.LoginRequest;
import com.hackerton.propofol.dto.ProfileResponse;
import com.hackerton.propofol.dto.TokenResponse;

public interface UserService {

    void join(JoinRequest joinRequest);
    TokenResponse login(LoginRequest loginRequest);
    ProfileResponse getProfile(Long userId);
}
