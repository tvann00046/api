package com.ra.base_spring_boot.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Một booking có thể có nhiều payment (retry thanh toán)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod; // VIETQR, VNPAY, VIETTEL_PAY, PAYPAL

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus; // PENDING, COMPLETED, FAILED, CANCELLED

    private LocalDateTime paymentTime;

    @Column(nullable = false)
    private Double amount;

    private String transactionId;

    public enum PaymentMethod {
        VIETQR, VNPAY, VIETTEL_PAY, PAYPAL
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, CANCELLED
    }
}
