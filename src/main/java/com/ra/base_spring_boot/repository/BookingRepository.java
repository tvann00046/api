package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}