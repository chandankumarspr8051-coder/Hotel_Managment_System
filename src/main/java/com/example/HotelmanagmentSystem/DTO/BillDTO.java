package com.example.HotelmanagmentSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Integer bookingId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String roomNumber;
    private String roomType;
    private Double pricePerNight;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Long totalNights;
    private Double roomSubtotal;      // Room charges

    // ✅ NEW - Food charges
    private List<FoodOrderResponseDTO> foodOrders;  // Khana list
    private Double foodTotal;
    private Double subtotal;           // pricePerNight × totalNights
    private Double tax;                // 10% of subtotal
    private Double totalAmount;        // subtotal + tax
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime generatedAt;
}