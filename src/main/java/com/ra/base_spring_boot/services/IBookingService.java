package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.model.Booking;
import java.util.List;

public interface IBookingService {
    List<Booking> getAll();
    Booking createBooking(Booking booking);
}
