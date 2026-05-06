// StaffResponseDTO.java
package com.example.HotelmanagmentSystem.DTO;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StaffResponseDTO {
    private Integer id;
    private String name;
    private String position;
    private Double salary;
    private String phone;
    private String email;
    private LocalDateTime joinedAt;
}