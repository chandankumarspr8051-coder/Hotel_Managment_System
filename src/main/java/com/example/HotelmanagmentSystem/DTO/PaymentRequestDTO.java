// PaymentRequestDTO.java
package com.example.HotelmanagmentSystem.DTO;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PaymentRequestDTO {

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be greater than 0")
    private Double amount;

    @NotBlank(message = "Payment method cannot be blank")
    @Pattern(regexp = "CASH|CARD|UPI", message = "Method must be CASH, CARD, or UPI")
    private String paymentMethod;

    @NotNull(message = "Booking ID cannot be null")
    @Positive(message = "Booking ID must be positive")
    private Integer bookingId;
//    private Double amount;
//    private String paymentMethod;
//    private Integer bookingId;
}