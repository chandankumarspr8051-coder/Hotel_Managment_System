package com.example.HotelmanagmentSystem.DTO;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FoodOrderRequestDTO {
    @Positive
    private Integer bookingId;

    @Positive
    private Integer foodMenuId;

    @Positive
    private Integer quantity;
}
