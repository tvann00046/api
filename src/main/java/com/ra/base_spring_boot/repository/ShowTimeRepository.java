package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.ShowTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Integer> {
    // Tìm kiếm theo tên phim
    Page<ShowTime> findByMovie_TitleContainingIgnoreCase(String title, Pageable pageable);
}