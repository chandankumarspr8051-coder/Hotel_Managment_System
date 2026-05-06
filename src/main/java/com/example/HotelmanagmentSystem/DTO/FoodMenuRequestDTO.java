package com.example.HotelmanagmentSystem.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FoodMenuRequestDTO {
    @NotBlank
    private String dishName;

    @Positive
    private Double price;

    private String description;
}

