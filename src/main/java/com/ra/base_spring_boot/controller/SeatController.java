package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.model.Seat;
import com.ra.base_spring_boot.services.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping
    public ResponseEntity<Page<Seat>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword) {
        Page<Seat> result = seatService.findAll(keyword, PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getById(@PathVariable Integer id) {
        return seatService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Seat> create(@RequestBody Seat seat) {
        return ResponseEntity.ok(seatService.save(seat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seat> update(@PathVariable Integer id, @RequestBody Seat seat) {
        return seatService.findById(id)
                .map(existing -> {
                    seat.setId(id);
                    return ResponseEntity.ok(seatService.save(seat));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        seatService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
