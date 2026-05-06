// RoomMapper.java
package com.example.HotelmanagmentSystem.Mapper;

import com.example.HotelmanagmentSystem.DTO.RoomRequestDTO;
import com.example.HotelmanagmentSystem.DTO.RoomResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Room;

public class RoomMapper {

    public static Room toEntity(RoomRequestDTO dto) {
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setType(dto.getType());
        room.setPrice(dto.getPrice());
        room.setDescription(dto.getDescription());
        return room;
    }

    public static RoomResponseDTO toResponse(Room room) {
        RoomResponseDTO dto = new RoomResponseDTO();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setType(room.getType());
        dto.setPrice(room.getPrice());
        dto.setAvailable(room.getAvailable());
        dto.setDescription(room.getDescription());
        return dto;
    }
}