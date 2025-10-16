package com.ra.base_spring_boot.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private String phone;
    private String address;
    private String status; // ACTIVE, BLOCKED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<UserRoleResponse> roles;
}
