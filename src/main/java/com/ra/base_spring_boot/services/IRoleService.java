package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.Role;
import com.ra.base_spring_boot.model.Role.RoleName;

import java.util.Optional;
import java.util.Set;

public interface IRoleService {
    Optional<Role> findByRoleName(RoleName roleName);
    Set<Role> getDefaultUserRoles();
}
