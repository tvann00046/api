package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Role;
import com.ra.base_spring_boot.model.Role.RoleName;
import com.ra.base_spring_boot.repository.IRoleRepository;
import com.ra.base_spring_boot.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Set<Role> getDefaultUserRoles() {
        Set<Role> roles = new HashSet<>();
        roleRepository.findByRoleName(RoleName.USER).ifPresent(roles::add);
        return roles;
    }
}
