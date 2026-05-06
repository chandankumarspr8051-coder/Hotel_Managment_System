package com.example.HotelmanagmentSystem.DTO;

import lombok.Data;

@Data
public class FoodOrderResponseDTO {
    private Integer id;
    private Integer bookingId;
    private String dishName;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
}
