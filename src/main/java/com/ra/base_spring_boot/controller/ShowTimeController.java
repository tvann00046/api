package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.model.ShowTime;
import com.ra.base_spring_boot.services.IShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowTimeController {
    private final IShowTimeService showTimeService;

    @GetMapping
    public ResponseEntity<Page<ShowTime>> getAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(showTimeService.getAll(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ShowTime>> search(@RequestParam String title,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(showTimeService.searchByMovieTitle(title, pageable));
    }

    @PostMapping
    public ResponseEntity<ShowTime> create(@RequestBody ShowTime showTime) {
        return ResponseEntity.ok(showTimeService.save(showTime));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowTime> update(@PathVariable Integer id, @RequestBody ShowTime showTime) {
        showTime.setId(id);
        return ResponseEntity.ok(showTimeService.save(showTime));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        showTimeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}