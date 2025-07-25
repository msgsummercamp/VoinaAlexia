package org.example.service;

import org.example.dto.SignInRequest;
import org.example.dto.SignInResponse;
import org.example.dto.UserRequest;

public interface AuthService {
    SignInResponse login(SignInRequest request);
    void register(UserRequest request);
}