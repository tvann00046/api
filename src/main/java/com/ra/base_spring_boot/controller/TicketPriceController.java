package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.model.TicketPrice;
import com.ra.base_spring_boot.services.TicketPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ticket-prices")
@RequiredArgsConstructor
public class TicketPriceController {

    private final TicketPriceService ticketPriceService;

    @GetMapping
    public ResponseEntity<Page<TicketPrice>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword) {
        Page<TicketPrice> result = ticketPriceService.findAll(keyword, PageRequest.of(page, size));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketPrice> getById(@PathVariable Long id) {
        return ticketPriceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TicketPrice> create(@RequestBody TicketPrice ticketPrice) {
        return ResponseEntity.ok(ticketPriceService.save(ticketPrice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketPrice> update(@PathVariable Long id, @RequestBody TicketPrice ticketPrice) {
        return ticketPriceService.findById(id)
                .map(existing -> {
                    ticketPrice.setId(id);
                    return ResponseEntity.ok(ticketPriceService.save(ticketPrice));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketPriceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
