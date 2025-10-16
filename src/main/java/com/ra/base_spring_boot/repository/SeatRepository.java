package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.Seat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    Page<Seat> findBySeatNumberContainingIgnoreCase(String keyword, Pageable pageable);
}
