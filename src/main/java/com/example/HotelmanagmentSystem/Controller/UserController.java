// UserController.java
package com.example.HotelmanagmentSystem.Controller;

import com.example.HotelmanagmentSystem.DTO.StaffRequestDTO;
import com.example.HotelmanagmentSystem.DTO.StaffResponseDTO;
import com.example.HotelmanagmentSystem.DTO.UserRequestDTO;
import com.example.HotelmanagmentSystem.DTO.UserResponseDTO;
import com.example.HotelmanagmentSystem.Repository.UserRepository;
import com.example.HotelmanagmentSystem.Service.StaffService;
import com.example.HotelmanagmentSystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;




    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());  // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));  // 200
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(requestDTO));  // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id,
                                                     @Valid @RequestBody UserRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.updateUser(id, requestDTO));  // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");  // 200
    }
}