package com.hackerton.propofol.service;

import com.hackerton.propofol.domain.User;
import com.hackerton.propofol.domain.repository.UserRepository;
import com.hackerton.propofol.dto.JoinRequest;
import com.hackerton.propofol.exception.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${image.upload.dir}")
    private String imageDirPath;

    @SneakyThrows
    @Override
    public void join(JoinRequest joinRequest) {
        userRepository.findByEmail(joinRequest.getEmail()).ifPresent(user -> {
            throw new UserDuplicateException();
        });

        String password = passwordEncoder.encode(joinRequest.getPassword());

        String imageName = UUID.randomUUID().toString();

        userRepository.save(
                User.builder()
                        .email(joinRequest.getEmail())
                        .password(password)
                        .name(joinRequest.getName())
                        .image(imageName)
                        .build()
        );

        joinRequest.getImage().transferTo(new File(imageDirPath, imageName));
    }
}
