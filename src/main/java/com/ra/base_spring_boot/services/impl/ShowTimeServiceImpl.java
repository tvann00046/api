package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.ShowTime;
import com.ra.base_spring_boot.repository.ShowTimeRepository;
import com.ra.base_spring_boot.services.IShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements IShowTimeService {
    private final ShowTimeRepository showTimeRepository;

    @Override
    public Page<ShowTime> getAll(Pageable pageable) {
        return showTimeRepository.findAll(pageable);
    }

    @Override
    public Page<ShowTime> searchByMovieTitle(String title, Pageable pageable) {
        return showTimeRepository.findByMovie_TitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Optional<ShowTime> findById(Integer id) {
        return showTimeRepository.findById(id);
    }

    @Override
    public ShowTime save(ShowTime showTime) {
        return showTimeRepository.save(showTime);
    }

    @Override
    public void deleteById(Integer id) {
        showTimeRepository.deleteById(id);
    }
}
