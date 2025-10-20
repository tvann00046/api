package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Festival;
import com.ra.base_spring_boot.repository.FestivalRepository;
import com.ra.base_spring_boot.services.IFestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements IFestivalService {
    private final FestivalRepository festivalRepository;

    @Override
    public List<Festival> findAll() {
        return festivalRepository.findAll();
    }

    @Override
    public Festival findById(Long id) {
        return festivalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Festival not found"));
    }

    @Override
    public Festival save(Festival festival) {
        if (festivalRepository.existsByTitle(festival.getTitle())) {
            throw new RuntimeException("Festival with title '" + festival.getTitle() + "' already exists!");
        }

        validateFestivalTime(festival);
        return festivalRepository.save(festival);
    }

    @Override
    public Festival update(Long id, Festival festival) {
        Festival existing = findById(id);
        validateFestivalTime(festival);

        if (!existing.getTitle().equals(festival.getTitle())
                && festivalRepository.existsByTitle(festival.getTitle())) {
            throw new RuntimeException("Festival with title '" + festival.getTitle() + "' already exists!");
        }

        existing.setTitle(festival.getTitle());
        existing.setImage(festival.getImage());
        existing.setStartTime(festival.getStartTime());
        existing.setEndTime(festival.getEndTime());
        return festivalRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        festivalRepository.deleteById(id);
    }

    private void validateFestivalTime(Festival festival) {
        LocalDateTime now = LocalDateTime.now();
        if (festival.getStartTime().isBefore(now)) {
            throw new RuntimeException("Thời gian bắt đầu phải sau thời gian hiện tại");
        }
        if (festival.getEndTime().isBefore(festival.getStartTime())) {
            throw new RuntimeException("Thời gian kết thúc phải sau thời gian bắt đầu");
        }
    }
}
