package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.dto.ScreenRequest;
import com.ra.base_spring_boot.dto.ScreenResponse;
import com.ra.base_spring_boot.exception.ResourceNotFoundException;
import com.ra.base_spring_boot.model.Screen;
import com.ra.base_spring_boot.model.Theater;
import com.ra.base_spring_boot.repository.ScreenRepository;
import com.ra.base_spring_boot.repository.TheaterRepository;
import com.ra.base_spring_boot.services.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterRepository theaterRepository;

    private ScreenResponse toDto(Screen s) {
        return ScreenResponse.builder()
                .id(s.getId())
                .name(s.getName())
                .seatCapacity(s.getSeatCapacity())
                .theater(s.getTheater() != null ? s.getTheater().getId() : null)
                .theaterName(s.getTheater() != null ? s.getTheater().getName() : null)
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public ScreenResponse create(ScreenRequest request) {
        Theater theater = theaterRepository.findById(request.getTheater())
                .orElseThrow(() -> new ResourceNotFoundException("Theater", "id", request.getTheater()));

        Screen screen = Screen.builder()
                .name(request.getName().trim())
                .seatCapacity(request.getSeatCapacity())
                .theater(theater)
                .build();

        Screen saved = screenRepository.save(screen);
        return toDto(saved);
    }

    @Override
    @Transactional
    public ScreenResponse update(Integer id, ScreenRequest request) {
        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Screen", "id", id));

        if (!screen.getName().equals(request.getName())) {
            screen.setName(request.getName().trim());
        }

        screen.setSeatCapacity(request.getSeatCapacity());

        if (!screen.getTheater().getId().equals(request.getTheater())) {
            Theater theater = theaterRepository.findById(request.getTheater())
                    .orElseThrow(() -> new ResourceNotFoundException("Theater", "id", request.getTheater()));
            screen.setTheater(theater);
        }

        Screen updated = screenRepository.save(screen);
        return toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Screen", "id", id));
        screenRepository.delete(screen);
    }

    @Override
    @Transactional(readOnly = true)
    public ScreenResponse getById(Integer id) {
        Screen screen = screenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Screen", "id", id));
        return toDto(screen);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ScreenResponse> search(String keyword, Integer theaterId, int page, int size) {
        if (page < 0) page = 0;
        if (size <= 0) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Screen> screens;
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        boolean hasTheater = theaterId != null;

        if (hasKeyword && hasTheater) {
            screens = screenRepository.findByNameContainingIgnoreCaseAndTheater_Id(keyword.trim(), theaterId, pageable);
        } else if (hasKeyword) {
            screens = screenRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        } else if (hasTheater) {
            screens = screenRepository.findByTheater_Id(theaterId, pageable);
        } else {
            screens = screenRepository.findAll(pageable);
        }

        return screens.map(this::toDto);
    }
}
