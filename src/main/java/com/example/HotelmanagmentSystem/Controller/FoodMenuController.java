package com.example.HotelmanagmentSystem.Controller;

import com.example.HotelmanagmentSystem.DTO.FoodMenuRequestDTO;
import com.example.HotelmanagmentSystem.DTO.FoodMenuResponseDTO;
import com.example.HotelmanagmentSystem.Service.FoodMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-menu")
@RequiredArgsConstructor
public class FoodMenuController {

    private final FoodMenuService foodMenuService;

    @GetMapping
    public ResponseEntity<List<FoodMenuResponseDTO>> getAllMenus() {
        return ResponseEntity.ok(foodMenuService.getAllMenus());
    }

    @PostMapping
    public ResponseEntity<FoodMenuResponseDTO> createMenu(
            @Valid @RequestBody FoodMenuRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodMenuService.createMenu(requestDTO));
    }
}
