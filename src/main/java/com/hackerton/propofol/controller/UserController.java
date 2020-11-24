package com.hackerton.propofol.controller;

import com.hackerton.propofol.dto.*;
import com.hackerton.propofol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public void join(@ModelAttribute @Valid JoinRequest joinRequest) {
        userService.join(joinRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/profile")
    public ProfileResponse getProfile(@RequestParam("userId") @Valid Long userId,
                                      Pageable pageable) {
        return userService.getProfile(userId, pageable);
    }

    @PutMapping("/profile")
    public void updateProfile(@ModelAttribute @Valid ProfileUpdateRequest profileUpdateRequest) {
        userService.updateProfile(profileUpdateRequest);
    }
}
