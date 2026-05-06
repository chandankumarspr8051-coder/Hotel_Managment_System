package com.example.HotelmanagmentSystem.Mapper;

import com.example.HotelmanagmentSystem.DTO.BookingResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Booking;

public class BookingMapper {

    public static BookingResponseDTO toResponse(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setCheckIn(booking.getCheckIn());
        dto.setCheckOut(booking.getCheckOut());
        dto.setStatus(booking.getStatus());
        dto.setTotalPrice(booking.getTotalPrice());
        dto.setCreatedAt(booking.getCreatedAt());

        if (booking.getUser() != null) {
            dto.setUserName(booking.getUser().getName());
            dto.setUserEmail(booking.getUser().getEmail());
        }

        if (booking.getRoom() != null) {
            dto.setRoomNumber(booking.getRoom().getRoomNumber());
            dto.setRoomType(booking.getRoom().getType());
        }

        return dto;
    }
}