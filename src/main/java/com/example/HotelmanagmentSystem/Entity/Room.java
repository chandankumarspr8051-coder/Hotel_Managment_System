package com.example.HotelmanagmentSystem.Entity;




import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    private String type; // SINGLE, DOUBLE, SUITE

    private Double price;

    private Boolean available = true; // default available

    private String description;
}
