package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.model.Theater;
import com.ra.base_spring_boot.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    // ✅ Lấy tất cả (phân trang)
    @GetMapping
    public ResponseEntity<Page<Theater>> getAllTheaters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(theaterService.findAll(pageable));
    }

    // ✅ Tìm kiếm theo tên hoặc địa điểm
    @GetMapping("/search")
    public ResponseEntity<Page<Theater>> searchTheaters(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(theaterService.search(keyword, pageable));
    }

    // ✅ Lấy chi tiết theo id
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getById(@PathVariable Integer id) {
        return theaterService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Thêm hoặc cập nhật
    @PostMapping
    public ResponseEntity<Theater> createOrUpdate(@RequestBody Theater theater) {
        return ResponseEntity.ok(theaterService.save(theater));
    }

    // ✅ Xóa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        theaterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
