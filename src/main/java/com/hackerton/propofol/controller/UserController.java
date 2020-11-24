package com.hackerton.propofol.controller;

import com.hackerton.propofol.dto.JoinRequest;
import com.hackerton.propofol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public void join(@ModelAttribute @Valid JoinRequest joinRequest) {
        userService.join(joinRequest);
    }
}
