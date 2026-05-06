package com.example.HotelmanagmentSystem.Entity;




import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate checkIn;

    private LocalDate checkOut;

    private String status; // PENDING, CONFIRMED, CANCELLED

    private Double totalPrice;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // User se link
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Room se link
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}