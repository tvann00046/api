package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface TheaterService {
    Page<Theater> findAll(Pageable pageable);
    Page<Theater> search(String keyword, Pageable pageable);
    Optional<Theater> findById(Integer id);
    Theater save(Theater theater);
    void delete(Integer id);
}
