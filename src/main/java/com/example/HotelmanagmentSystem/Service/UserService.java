package com.example.HotelmanagmentSystem.Service;

import com.example.HotelmanagmentSystem.DTO.UserRequestDTO;
import com.example.HotelmanagmentSystem.DTO.UserResponseDTO;
import com.example.HotelmanagmentSystem.Entity.User;
import com.example.HotelmanagmentSystem.Exception.DuplicateResourceException;
import com.example.HotelmanagmentSystem.Exception.ResourceNotFoundException;
import com.example.HotelmanagmentSystem.Mapper.UserMapper;
import com.example.HotelmanagmentSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + id));
        return UserMapper.toResponse(user);
    }

    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException(
                    "Email already exists: " + requestDTO.getEmail());
        }
        User user = UserMapper.toEntity(requestDTO);
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        return UserMapper.toResponse(userRepository.save(user));
    }

    public UserResponseDTO updateUser(Integer id, UserRequestDTO requestDTO) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + id));
        existing.setName(requestDTO.getName());
        existing.setPhone(requestDTO.getPhone());
        existing.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        return UserMapper.toResponse(userRepository.save(existing));
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with id: " + id));
        userRepository.deleteById(id);
    }
}