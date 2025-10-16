package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SeatService {
    Page<Seat> findAll(String keyword, Pageable pageable);
    Optional<Seat> findById(Integer id);
    Seat save(Seat seat);
    void deleteById(Integer id);
}
