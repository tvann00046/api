package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.TicketPrice;
import com.ra.base_spring_boot.repository.TicketPriceRepository;
import com.ra.base_spring_boot.services.TicketPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketPriceServiceImpl implements TicketPriceService {

    private final TicketPriceRepository ticketPriceRepository;

    @Override
    public Page<TicketPrice> findAll(String keyword, Pageable pageable) {
        if (keyword != null && !keyword.isEmpty()) {
            return ticketPriceRepository.findByTypeSeatContainingIgnoreCase(keyword, pageable);
        }
        return ticketPriceRepository.findAll(pageable);
    }

    @Override
    public Optional<TicketPrice> findById(Long id) {
        return ticketPriceRepository.findById(id);
    }

    @Override
    public TicketPrice save(TicketPrice ticketPrice) {
        return ticketPriceRepository.save(ticketPrice);
    }

    @Override
    public void deleteById(Long id) {
        ticketPriceRepository.deleteById(id);
    }
}
