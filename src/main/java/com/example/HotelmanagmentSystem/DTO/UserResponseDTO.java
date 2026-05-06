// UserResponseDTO.java
package com.example.HotelmanagmentSystem.DTO;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private LocalDateTime createdAt;
}