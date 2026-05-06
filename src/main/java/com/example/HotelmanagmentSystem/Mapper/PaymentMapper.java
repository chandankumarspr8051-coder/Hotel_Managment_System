// PaymentMapper.java
package com.example.HotelmanagmentSystem.Mapper;

import com.example.HotelmanagmentSystem.DTO.PaymentResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Payment;

public class PaymentMapper {

    public static PaymentResponseDTO toResponse(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setBookingId(payment.getBooking().getId());
        dto.setUserName(payment.getBooking().getUser().getName());
        dto.setRoomNumber(payment.getBooking().getRoom().getRoomNumber());
        return dto;
    }
}