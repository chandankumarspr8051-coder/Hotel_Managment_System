// RoomService.java
package com.example.HotelmanagmentSystem.Service;

import com.example.HotelmanagmentSystem.DTO.RoomRequestDTO;
import com.example.HotelmanagmentSystem.DTO.RoomResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Room;
import com.example.HotelmanagmentSystem.Mapper.RoomMapper;
import com.example.HotelmanagmentSystem.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomResponseDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toResponse)
                .collect(Collectors.toList());
    }

    public RoomResponseDTO getRoomById(Integer id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        return RoomMapper.toResponse(room);
    }

    // Service ke andar entity chahiye BookingService ke liye
    public Room getRoomEntityById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    public List<RoomResponseDTO> getAvailableRooms() {
        return roomRepository.findByAvailable(true)
                .stream()
                .map(RoomMapper::toResponse)
                .collect(Collectors.toList());
    }

    public RoomResponseDTO createRoom(RoomRequestDTO requestDTO) {
        if (roomRepository.existsByRoomNumber(requestDTO.getRoomNumber())) {
            throw new RuntimeException("Room number already exists: " + requestDTO.getRoomNumber());
        }
        Room room = RoomMapper.toEntity(requestDTO);
        return RoomMapper.toResponse(roomRepository.save(room));
    }

    public RoomResponseDTO updateRoom(Integer id, RoomRequestDTO requestDTO) {
        Room existing = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        existing.setType(requestDTO.getType());
        existing.setPrice(requestDTO.getPrice());
        existing.setDescription(requestDTO.getDescription());
        return RoomMapper.toResponse(roomRepository.save(existing));
    }

    public void deleteRoom(Integer id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found with id: " + id);
        }
        roomRepository.deleteById(id);
    }
}