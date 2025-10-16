package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.dto.LoginRequest;
import com.ra.base_spring_boot.dto.RegisterRequest;
import com.ra.base_spring_boot.dto.JwtResponse;
import com.ra.base_spring_boot.model.User;
import com.ra.base_spring_boot.model.Role;
import com.ra.base_spring_boot.model.Role.RoleName;
import com.ra.base_spring_boot.repository.IUserRepository;
import com.ra.base_spring_boot.repository.IRoleRepository;
import com.ra.base_spring_boot.security.jwt.JwtProvider;
import com.ra.base_spring_boot.services.IAuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        String role = authentication.getAuthorities().stream()
                .findFirst().get().getAuthority();

        return JwtResponse.builder()
                .token(token)
                .email(request.getEmail())
                .role(List.of(() -> role))
                .build();
    }

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã tồn tại!");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(User.Status.ACTIVE)
                .build();

        Role roleUser = roleRepository.findByRoleName(RoleName.USER)
                .orElseThrow(() -> new RuntimeException("Role USER không tồn tại"));

        user.getRoles().add(roleUser);
        userRepository.save(user);

        userRepository.save(user);
        return user;
    }
}
