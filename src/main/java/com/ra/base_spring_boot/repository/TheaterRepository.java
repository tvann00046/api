package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    @Query("SELECT t FROM Theater t WHERE " +
            "LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.location) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Theater> search(String keyword, Pageable pageable);
}
