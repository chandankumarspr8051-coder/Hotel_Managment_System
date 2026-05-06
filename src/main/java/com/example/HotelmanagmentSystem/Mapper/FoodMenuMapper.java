package com.example.HotelmanagmentSystem.Mapper;
import com.example.HotelmanagmentSystem.Entity.FoodMenu;
import com.example.HotelmanagmentSystem.DTO.FoodMenuResponseDTO;

public class FoodMenuMapper {
    public static FoodMenuResponseDTO toResponse(FoodMenu menu) {
        FoodMenuResponseDTO dto = new FoodMenuResponseDTO();
        dto.setId(menu.getId());
        dto.setDishName(menu.getDishName());
        dto.setPrice(menu.getPrice());
        dto.setDescription(menu.getDescription());
        return dto;
    }
}