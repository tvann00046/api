package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.dto.ScreenResponse;
import com.ra.base_spring_boot.model.Screen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {

    Page<Screen> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Screen> findByTheater_Id(Integer theaterId, Pageable pageable);

    Page<Screen> findByNameContainingIgnoreCaseAndTheater_Id(String name, Integer theaterId, Pageable pageable);

    Screen save(ScreenResponse screen);
}
