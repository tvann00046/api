package com.ra.base_spring_boot.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@SuppressWarnings("unused")
@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
@Data
public class UserRole {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "role_id")
    private Integer roleId;
}
