package com.hackerton.propofol.controller;

import com.hackerton.propofol.dto.*;
import com.hackerton.propofol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ProfileResponse getProfile(@PageableDefault(size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.getProfile(pageable);
    }

    @PutMapping("/profile")
    public void updateProfile(@ModelAttribute @Valid ProfileUpdateRequest profileUpdateRequest) {
        userService.updateProfile(profileUpdateRequest);
    }
}
