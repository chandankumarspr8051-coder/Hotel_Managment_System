// StaffRequestDTO.java
package com.example.HotelmanagmentSystem.DTO;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StaffRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
    private String name;

    @NotBlank(message = "Position cannot be blank")
    @Pattern(regexp = "MANAGER|RECEPTIONIST|HOUSEKEEPING", message = "Position must be MANAGER, RECEPTIONIST, or HOUSEKEEPING")
    private String position;

    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be greater than 0")
    private Double salary;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
//    private String name;
//    private String position;
//    private Double salary;
//    private String phone;
//    private String email;
}