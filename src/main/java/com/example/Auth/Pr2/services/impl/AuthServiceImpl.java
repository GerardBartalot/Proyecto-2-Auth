package com.example.Auth.Pr2.services.impl;

import com.example.Auth.Pr2.commons.dtos.TokenResponse;
import com.example.Auth.Pr2.commons.dtos.UserRequest;
import com.example.Auth.Pr2.commons.entities.UserModel;
import com.example.Auth.Pr2.repositories.UserRepository;
import com.example.Auth.Pr2.services.AuthService;
import com.example.Auth.Pr2.services.JwtService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(userRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}