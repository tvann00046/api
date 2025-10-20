package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.Festival;
import java.util.List;

public interface IFestivalService {
    List<Festival> findAll();
    Festival findById(Long id);
    Festival save(Festival festival);
    Festival update(Long id, Festival festival);
    void delete(Long id);
}
