package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.dto.ScreenRequest;
import com.ra.base_spring_boot.dto.ScreenResponse;
import org.springframework.data.domain.Page;

public interface ScreenService {
    ScreenResponse create(ScreenRequest request);
    ScreenResponse update(Integer id, ScreenRequest request);
    void delete(Integer id);
    ScreenResponse getById(Integer id);
    Page<ScreenResponse> search(String keyword, Integer theaterId, int page, int size);
}
