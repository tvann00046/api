package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.model.Festival;
import com.ra.base_spring_boot.services.IFestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/festivals")
@RequiredArgsConstructor
public class FestivalController {

    private final IFestivalService festivalService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public List<Festival> getAll() {
        return festivalService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public Festival getById(@PathVariable Long id) {
        return festivalService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Festival create(@RequestBody Festival festival) {
        return festivalService.save(festival);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Festival update(@PathVariable Long id, @RequestBody Festival festival) {
        return festivalService.update(id, festival);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        festivalService.delete(id);
    }
}
