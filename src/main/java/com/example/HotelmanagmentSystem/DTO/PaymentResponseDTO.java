// PaymentResponseDTO.java
package com.example.HotelmanagmentSystem.DTO;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentResponseDTO {
    private Integer id;
    private Double amount;
    private String paymentMethod;
    private String status;
    private LocalDateTime paymentDate;
    private Integer bookingId;
    private String userName;
    private String roomNumber;
}