package com.ra.base_spring_boot.config;

import com.ra.base_spring_boot.model.Role;
import com.ra.base_spring_boot.model.User;
import com.ra.base_spring_boot.repository.IRoleRepository;
import com.ra.base_spring_boot.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            roleRepository.findByRoleName(Role.RoleName.ADMIN)
                    .orElseGet(() -> roleRepository.save(Role.builder()
                            .roleName(Role.RoleName.ADMIN)
                            .build()));

            roleRepository.findByRoleName(Role.RoleName.USER)
                    .orElseGet(() -> roleRepository.save(Role.builder()
                            .roleName(Role.RoleName.USER)
                            .build()));


            String adminEmail = "admin@gmail.com";

            if (!userRepository.existsByEmail(adminEmail)) {
                Role adminRole = roleRepository.findByRoleName(Role.RoleName.ADMIN)
                        .orElseThrow(() -> new RuntimeException("Admin role not found"));

                User admin = User.builder()
                        .firstName("Admin")
                        .lastName("System")
                        .email(adminEmail)
                        .password(passwordEncoder.encode("123456"))
                        .roles(Set.of(adminRole))
                        .build();

                userRepository.save(admin);
                System.out.println("✅ Default admin created: " + adminEmail + " / 123456");
            } else {
                System.out.println("ℹ️ Admin user already exists, skipping creation.");
            }
        };
    }
}
