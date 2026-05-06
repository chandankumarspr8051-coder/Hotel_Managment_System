package com.example.HotelmanagmentSystem.Entity;



import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    private String paymentMethod; // CASH, CARD, UPI

    private String status; // PENDING, COMPLETED, FAILED

    @Column(updatable = false)
    private LocalDateTime paymentDate;

    @PrePersist
    public void onCreate() {
        this.paymentDate = LocalDateTime.now();
    }

    // Booking se link
    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
