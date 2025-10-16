package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.dto.JwtResponse;
import com.ra.base_spring_boot.dto.LoginRequest;
import com.ra.base_spring_boot.dto.RegisterRequest;
import com.ra.base_spring_boot.model.User;

public interface IAuthService {
    JwtResponse login(LoginRequest loginRequest);
    User register(RegisterRequest registerRequest);
}
