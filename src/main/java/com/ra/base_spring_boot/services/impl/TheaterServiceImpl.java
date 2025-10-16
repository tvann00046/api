package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Theater;
import com.ra.base_spring_boot.repository.TheaterRepository;
import com.ra.base_spring_boot.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheaterServiceImpl implements TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public Page<Theater> findAll(Pageable pageable) {
        return theaterRepository.findAll(pageable);
    }

    @Override
    public Page<Theater> search(String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return theaterRepository.findAll(pageable);
        }
        return theaterRepository.search(keyword, pageable);
    }

    @Override
    public Optional<Theater> findById(Integer id) {
        return theaterRepository.findById(id);
    }

    @Override
    public Theater save(Theater theater) {
        theater.setUpdatedAt(java.time.LocalDateTime.now());
        return theaterRepository.save(theater);
    }

    @Override
    public void delete(Integer id) {
        theaterRepository.deleteById(id);
    }
}

