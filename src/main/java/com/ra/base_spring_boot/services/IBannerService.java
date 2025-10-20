package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.Banner;
import java.util.List;

public interface IBannerService {
    List<Banner> findAll();
    Banner findById(Long id);
    Banner save(Banner banner);
    Banner update(Long id, Banner banner);
    void delete(Long id);
}
