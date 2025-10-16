package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.dto.ScreenRequest;
import com.ra.base_spring_boot.dto.ScreenResponse;
import com.ra.base_spring_boot.services.ScreenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/screens")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    @GetMapping
    public ResponseEntity<Page<ScreenResponse>> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer theaterId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ScreenResponse> result = screenService.search(keyword, theaterId, page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(screenService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ScreenResponse> create(@Valid @RequestBody ScreenRequest request) {
        ScreenResponse created = screenService.create(request);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScreenResponse> update(@PathVariable Integer id,
                                                 @Valid @RequestBody ScreenRequest request) {
        ScreenResponse updated = screenService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        screenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
