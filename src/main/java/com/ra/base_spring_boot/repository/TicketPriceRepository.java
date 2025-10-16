package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.TicketPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPriceRepository extends JpaRepository<TicketPrice, Long> {
    Page<TicketPrice> findByTypeSeatContainingIgnoreCase(String keyword, Pageable pageable);
}
