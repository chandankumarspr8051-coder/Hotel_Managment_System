// BookingResponseDTO.java
package com.example.HotelmanagmentSystem.DTO;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BookingResponseDTO {
    private Integer id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String status;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private String userName;
    private String userEmail;
    private String roomNumber;
    private String roomType;
}