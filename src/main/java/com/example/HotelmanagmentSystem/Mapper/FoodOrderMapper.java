package com.example.HotelmanagmentSystem.Mapper;
import com.example.HotelmanagmentSystem.Entity.FoodOrder;
import com.example.HotelmanagmentSystem.DTO.FoodOrderResponseDTO;

public class FoodOrderMapper {
    public static FoodOrderResponseDTO toResponse(FoodOrder order) {
        FoodOrderResponseDTO dto = new FoodOrderResponseDTO();
        dto.setId(order.getId());
        dto.setBookingId(order.getBooking().getId());
        dto.setDishName(order.getFoodMenu().getDishName());
        dto.setQuantity(order.getQuantity());
        dto.setPrice(order.getFoodMenu().getPrice());
        dto.setTotalPrice(order.getTotalPrice());
        return dto;
    }
}

