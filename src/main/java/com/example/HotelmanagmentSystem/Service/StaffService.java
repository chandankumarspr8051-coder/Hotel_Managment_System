// StaffService.java
package com.example.HotelmanagmentSystem.Service;

import com.example.HotelmanagmentSystem.DTO.StaffRequestDTO;
import com.example.HotelmanagmentSystem.DTO.StaffResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Staff;
import com.example.HotelmanagmentSystem.Mapper.StaffMapper;
import com.example.HotelmanagmentSystem.Repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public List<StaffResponseDTO> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(StaffMapper::toResponse)
                .collect(Collectors.toList());
    }

    public StaffResponseDTO getStaffById(Integer id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
        return StaffMapper.toResponse(staff);
    }

    public StaffResponseDTO createStaff(StaffRequestDTO requestDTO) {
        if (staffRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("Staff email already exists!");
        }
        Staff staff = StaffMapper.toEntity(requestDTO);
        return StaffMapper.toResponse(staffRepository.save(staff));
    }

    public StaffResponseDTO updateStaff(Integer id, StaffRequestDTO requestDTO) {
        Staff existing = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + id));
        existing.setName(requestDTO.getName());
        existing.setPosition(requestDTO.getPosition());
        existing.setSalary(requestDTO.getSalary());
        existing.setPhone(requestDTO.getPhone());
        return StaffMapper.toResponse(staffRepository.save(existing));
    }

    public void deleteStaff(Integer id) {
        if (!staffRepository.existsById(id)) {
            throw new RuntimeException("Staff not found with id: " + id);
        }
        staffRepository.deleteById(id);
    }
}