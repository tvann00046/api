package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.ShowTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IShowTimeService {
    Page<ShowTime> getAll(Pageable pageable);
    Page<ShowTime> searchByMovieTitle(String title, Pageable pageable);
    Optional<ShowTime> findById(Integer id);
    ShowTime save(ShowTime showTime);
    void deleteById(Integer id);
}
