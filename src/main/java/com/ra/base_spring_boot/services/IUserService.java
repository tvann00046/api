package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findById(Integer id);
    boolean existsById(Integer id);
    User save(User user);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    
}
