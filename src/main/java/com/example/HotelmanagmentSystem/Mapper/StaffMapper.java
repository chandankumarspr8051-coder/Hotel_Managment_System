// StaffMapper.java
package com.example.HotelmanagmentSystem.Mapper;

import com.example.HotelmanagmentSystem.DTO.StaffRequestDTO;
import com.example.HotelmanagmentSystem.DTO.StaffResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Staff;

public class StaffMapper {

    public static Staff toEntity(StaffRequestDTO dto) {
        Staff staff = new Staff();
        staff.setName(dto.getName());
        staff.setPosition(dto.getPosition());
        staff.setSalary(dto.getSalary());
        staff.setPhone(dto.getPhone());
        staff.setEmail(dto.getEmail());
        return staff;
    }

    public static StaffResponseDTO toResponse(Staff staff) {
        StaffResponseDTO dto = new StaffResponseDTO();
        dto.setId(staff.getId());
        dto.setName(staff.getName());
        dto.setPosition(staff.getPosition());
        dto.setSalary(staff.getSalary());
        dto.setPhone(staff.getPhone());
        dto.setEmail(staff.getEmail());
        dto.setJoinedAt(staff.getJoinedAt());
        return dto;
    }
}