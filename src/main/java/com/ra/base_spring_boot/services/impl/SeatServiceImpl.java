package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Seat;
import com.ra.base_spring_boot.repository.SeatRepository;
import com.ra.base_spring_boot.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Override
    public Page<Seat> findAll(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return seatRepository.findBySeatNumberContainingIgnoreCase(keyword, pageable);
        }
        return seatRepository.findAll(pageable);
    }

    @Override
    public Optional<Seat> findById(Integer id) {
        return seatRepository.findById(id);
    }

    @Override
    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }

    @Override
    public void deleteById(Integer id) {
        seatRepository.deleteById(id);
    }
}
