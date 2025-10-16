package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.dto.*;
import com.ra.base_spring_boot.model.User;
import com.ra.base_spring_boot.security.jwt.JwtProvider;
import com.ra.base_spring_boot.services.IRoleService;
import com.ra.base_spring_boot.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtTokenProvider;

    

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Xác thực password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), request.getPassword())
        );

        String token = jwtTokenProvider.generateToken(user.getEmail());

        return ResponseEntity.ok(
                AuthResponse.builder()
                        .token(token)
                        .user(toUserResponse(user))
                        .build()
        );
    }

    // GET CURRENT USER THEO ID từ token
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.replace("Bearer ", "");
        String email = jwtTokenProvider.getEmailFromToken(token);
        User user = userService.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(toUserResponse(user));
    }


    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .address(request.getAddress())
                .roles(roleService.getDefaultUserRoles())
                .build();

        User saved = userService.save(user);
        return ResponseEntity.ok(toUserResponse(saved));
    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .address(user.getAddress())
                .status(user.getStatus().name())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .roles(user.getRoles().stream()
                        .map(r -> new UserRoleResponse(r.getRoleName().name()))
                        .toList())
                .build();
    }
}
