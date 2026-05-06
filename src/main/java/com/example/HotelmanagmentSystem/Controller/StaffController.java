// StaffController.java
package com.example.HotelmanagmentSystem.Controller;

import com.example.HotelmanagmentSystem.DTO.StaffRequestDTO;
import com.example.HotelmanagmentSystem.DTO.StaffResponseDTO;
import com.example.HotelmanagmentSystem.Service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());  // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> getStaffById(@PathVariable Integer id) {
        return ResponseEntity.ok(staffService.getStaffById(id));  // 200
    }

    @PostMapping
    public ResponseEntity<StaffResponseDTO> createStaff(@Valid @RequestBody StaffRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(staffService.createStaff(requestDTO));  // 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffResponseDTO> updateStaff(@PathVariable Integer id,
                                                       @Valid @RequestBody StaffRequestDTO requestDTO) {
        return ResponseEntity.ok(staffService.updateStaff(id, requestDTO));  // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Integer id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Staff deleted successfully");  // 200
    }
}