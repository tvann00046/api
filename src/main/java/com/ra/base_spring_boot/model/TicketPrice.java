package com.ra.base_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "ticket_prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatType typeSeat;  // STANDARD, VIP, SWEETBOX

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovieType typeMovie; // 2D, 3D

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Boolean dayType; // false = weekday, true = weekend/holiday

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    public enum SeatType {
        STANDARD, VIP, SWEETBOX
    }

    public enum MovieType {
        _2D, _3D
    }
}
