// BookingController.java
package com.example.HotelmanagmentSystem.Controller;

import com.example.HotelmanagmentSystem.DTO.BillDTO;
import com.example.HotelmanagmentSystem.DTO.BookingRequestDTO;
import com.example.HotelmanagmentSystem.DTO.BookingResponseDTO;
import com.example.HotelmanagmentSystem.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;



    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());  // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));  // 200
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));  // 200
    }

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.createBooking(requestDTO));  // 201
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingResponseDTO> updateStatus(@PathVariable Integer id,
                                                          @Valid @RequestParam String status) {
        return ResponseEntity.ok(bookingService.updateStatus(id, status));  // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully");  // 200
    }
}