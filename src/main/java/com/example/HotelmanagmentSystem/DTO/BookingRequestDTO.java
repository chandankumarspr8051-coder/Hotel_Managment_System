// BookingRequestDTO.java
package com.example.HotelmanagmentSystem.DTO;
import lombok.Data;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class BookingRequestDTO {

    @NotNull(message = "Check-in date cannot be null")
    @FutureOrPresent(message = "Check-in date must be today or future")
    private LocalDate checkIn;

    @NotNull(message = "Check-out date cannot be null")
    @Future(message = "Check-out date must be in future")
    private LocalDate checkOut;
    @Positive
    private Double roomPrice;  // Price per night

    @Positive
    private Integer nights;    // Number of nights

    @NotNull(message = "Total price cannot be null")
    @Positive(message = "Total price must be greater than 0")
    private Double totalPrice;

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be positive")
    private Integer userId;

    @NotNull(message = "Room ID cannot be null")
    @Positive(message = "Room ID must be positive")
    private Integer roomId;
//    private LocalDate checkIn;
//    private LocalDate checkOut;
//    private Double totalPrice;
//    private Integer userId;
//    private Integer roomId;
}