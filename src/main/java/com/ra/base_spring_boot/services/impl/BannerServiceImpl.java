package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Banner;
import com.ra.base_spring_boot.repository.BannerRepository;
import com.ra.base_spring_boot.services.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements IBannerService {
    private final BannerRepository bannerRepository;

    @Override
    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner findById(Long id) {
        return bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found"));
    }

    @Override
    public Banner save(Banner banner) {
        if (bannerRepository.existsByUrl(banner.getUrl())) {
            throw new RuntimeException("Banner with URL '" + banner.getUrl() + "' already exists!");
        }

        return bannerRepository.save(banner);
    }

    @Override
    public Banner update(Long id, Banner banner) {
        Banner existing = findById(id);

        if (!existing.getUrl().equals(banner.getUrl())
                && bannerRepository.existsByUrl(banner.getUrl())) {
            throw new RuntimeException("Banner with URL '" + banner.getUrl() + "' already exists!");
        }

        existing.setUrl(banner.getUrl());
        existing.setType(banner.getType());
        existing.setPosition(banner.getPosition());
        return bannerRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        bannerRepository.deleteById(id);
    }
}
