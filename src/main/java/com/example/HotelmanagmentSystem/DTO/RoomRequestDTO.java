// RoomRequestDTO.java
package com.example.HotelmanagmentSystem.DTO;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RoomRequestDTO {

    @NotBlank(message = "Room number cannot be blank")
    private String roomNumber;

    @NotBlank(message = "Room type cannot be blank")
    @Pattern(regexp = "SINGLE|DOUBLE|SUITE", message = "Type must be SINGLE, DOUBLE, or SUITE")
    private String type;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @Size(max = 500, message = "Description max 500 characters")
    private String description;
//    private String roomNumber;
//    private String type;
//    private Double price;
//    private String description;
}