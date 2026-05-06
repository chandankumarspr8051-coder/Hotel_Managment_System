package com.example.HotelmanagmentSystem.Service;

import com.example.HotelmanagmentSystem.DTO.FoodMenuRequestDTO;
import com.example.HotelmanagmentSystem.DTO.FoodMenuResponseDTO;
import com.example.HotelmanagmentSystem.Entity.FoodMenu;
import com.example.HotelmanagmentSystem.Mapper.FoodMenuMapper;
import com.example.HotelmanagmentSystem.Repository.FoodMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodMenuService {

    private final FoodMenuRepository foodMenuRepository;

    public List<FoodMenuResponseDTO> getAllMenus() {
        return foodMenuRepository.findAll()
                .stream()
                .map(FoodMenuMapper::toResponse)
                .collect(Collectors.toList());
    }

    public FoodMenuResponseDTO createMenu(FoodMenuRequestDTO requestDTO) {
        FoodMenu menu = new FoodMenu();
        menu.setDishName(requestDTO.getDishName());
        menu.setPrice(requestDTO.getPrice());
        menu.setDescription(requestDTO.getDescription());
        menu.setCreatedAt(LocalDateTime.now());

        return FoodMenuMapper.toResponse(foodMenuRepository.save(menu));
    }
}
