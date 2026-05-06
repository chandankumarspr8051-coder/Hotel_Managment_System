// RoomController.java
package com.example.HotelmanagmentSystem.Controller;

import com.example.HotelmanagmentSystem.DTO.RoomRequestDTO;
import com.example.HotelmanagmentSystem.DTO.RoomResponseDTO;
import com.example.HotelmanagmentSystem.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());  // 200
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomResponseDTO>> getAvailableRooms() {
        return ResponseEntity.ok(roomService.getAvailableRooms());  // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Integer id) {
        return ResponseEntity.ok(roomService.getRoomById(id));  // 200
    }

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(@Valid @RequestBody RoomRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomService.createRoom(requestDTO));  // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable Integer id,
                                                    @Valid @RequestBody RoomRequestDTO requestDTO) {
        return ResponseEntity.ok(roomService.updateRoom(id, requestDTO));  // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");  // 200
    }
}