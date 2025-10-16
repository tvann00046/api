package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.TicketPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TicketPriceService {
    Page<TicketPrice> findAll(String keyword, Pageable pageable);
    Optional<TicketPrice> findById(Long id);
    TicketPrice save(TicketPrice ticketPrice);
    void deleteById(Long id);
}
