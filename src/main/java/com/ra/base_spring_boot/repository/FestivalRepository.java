package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
    boolean existsByTitle(String title);
}


