package com.hackerton.propofol.service;

import com.hackerton.propofol.dto.*;

import org.springframework.data.domain.Pageable;

public interface UserService {

    void join(JoinRequest joinRequest);
    TokenResponse login(LoginRequest loginRequest);
    ProfileResponse getProfile(Long userId, Pageable pageable);
    void updateProfile(ProfileUpdateRequest profileUpdateRequest);
}
