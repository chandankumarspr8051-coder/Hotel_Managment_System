// RoomResponseDTO.java
package com.example.HotelmanagmentSystem.DTO;
import lombok.Data;

@Data
public class RoomResponseDTO {
    private Integer id;
    private String roomNumber;
    private String type;
    private Double price;
    private Boolean available;
    private String description;
}