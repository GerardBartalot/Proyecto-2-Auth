package com.example.Auth.Pr2.controllers;

import com.example.Auth.Pr2.commons.constants.ApiPathConstants;
import com.example.Auth.Pr2.commons.dtos.TokenResponse;
import com.example.Auth.Pr2.commons.dtos.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ApiPathConstants.VI_ROUTE + ApiPathConstants.AUTH_ROUTE)
public interface AuthApi {
    @PostMapping(value = "/register")
    ResponseEntity<TokenResponse> registerUser(@RequestBody @Valid UserRequest userRequest);

    @PostMapping(value = "/login")
    ResponseEntity<TokenResponse> loginUser(@RequestBody @Valid UserRequest userRequest);
}
