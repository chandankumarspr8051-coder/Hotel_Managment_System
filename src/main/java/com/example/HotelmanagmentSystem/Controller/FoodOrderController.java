package com.example.HotelmanagmentSystem.Controller;

import com.example.HotelmanagmentSystem.DTO.FoodOrderRequestDTO;
import com.example.HotelmanagmentSystem.DTO.FoodOrderResponseDTO;
import com.example.HotelmanagmentSystem.Service.FoodOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-orders")
@RequiredArgsConstructor
public class FoodOrderController {

    private final FoodOrderService foodOrderService;

    @PostMapping
    public ResponseEntity<FoodOrderResponseDTO> createOrder(
            @Valid @RequestBody FoodOrderRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodOrderService.createOrder(requestDTO));
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<FoodOrderResponseDTO>> getOrdersByBooking(
            @PathVariable Integer bookingId) {
        return ResponseEntity.ok(foodOrderService.getOrdersByBooking(bookingId));
    }
}
