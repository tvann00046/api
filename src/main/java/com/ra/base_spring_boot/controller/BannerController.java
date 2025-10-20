package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.model.Banner;
import com.ra.base_spring_boot.services.IBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banners")
@RequiredArgsConstructor
public class BannerController {

    private final IBannerService bannerService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<Banner> getAll() {
        return bannerService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Banner getById(@PathVariable Long id) {
        return bannerService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Banner create(@RequestBody Banner banner) {
        return bannerService.save(banner);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Banner update(@PathVariable Long id, @RequestBody Banner banner) {
        return bannerService.update(id, banner);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        bannerService.delete(id);
    }
}
