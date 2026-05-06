package com.example.HotelmanagmentSystem.Entity;



import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String position; // MANAGER, RECEPTIONIST, HOUSEKEEPING

    private Double salary;

    private String phone;

    private String email;

    @Column(updatable = false)
    private LocalDateTime joinedAt;

    @PrePersist
    public void onCreate() {
        this.joinedAt = LocalDateTime.now();
    }
}
