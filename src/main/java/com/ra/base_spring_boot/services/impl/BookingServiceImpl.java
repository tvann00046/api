package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.model.Booking;
import com.ra.base_spring_boot.repository.BookingRepository;
import com.ra.base_spring_boot.services.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {
    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking createBooking(Booking booking) {
        booking.setCreatedAt(java.time.LocalDateTime.now());
        return bookingRepository.save(booking);
    }
}
