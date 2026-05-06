package com.example.HotelmanagmentSystem.Service;

import com.example.HotelmanagmentSystem.DTO.BillDTO;
import com.example.HotelmanagmentSystem.DTO.FoodOrderResponseDTO;
import com.example.HotelmanagmentSystem.DTO.PaymentRequestDTO;
import com.example.HotelmanagmentSystem.DTO.PaymentResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Booking;
import com.example.HotelmanagmentSystem.Entity.FoodOrder;
import com.example.HotelmanagmentSystem.Entity.Payment;
import com.example.HotelmanagmentSystem.Entity.Room;
import com.example.HotelmanagmentSystem.Exception.InvalidBookingException;
import com.example.HotelmanagmentSystem.Exception.ResourceNotFoundException;
import com.example.HotelmanagmentSystem.Mapper.FoodOrderMapper;
import com.example.HotelmanagmentSystem.Mapper.PaymentMapper;
import com.example.HotelmanagmentSystem.Repository.BookingRepository;
import com.example.HotelmanagmentSystem.Repository.FoodOrderRepository;
import com.example.HotelmanagmentSystem.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    @Autowired
    private final FoodOrderRepository foodOrderRepository;

    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(PaymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PaymentResponseDTO getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found with id: " + id));
        return PaymentMapper.toResponse(payment);
    }

    public PaymentResponseDTO createPayment(PaymentRequestDTO requestDTO) {
        Booking booking = bookingRepository.findById(requestDTO.getBookingId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Booking not found with id: " + requestDTO.getBookingId()));

        // ✅ VALIDATION: Amount check karo
        Room room = booking.getRoom();
        Long totalNights = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
        Double expectedAmount = room.getPrice() * totalNights;

        if (!requestDTO.getAmount().equals(expectedAmount)) {
            throw new InvalidBookingException(
                    "Invalid amount! Expected: " + expectedAmount + ", Got: " + requestDTO.getAmount());
        }

        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);

        Payment payment = new Payment();
        payment.setAmount(requestDTO.getAmount());
        payment.setPaymentMethod(requestDTO.getPaymentMethod());
        payment.setStatus("COMPLETED");
        payment.setBooking(booking);

        return PaymentMapper.toResponse(paymentRepository.save(payment));
    }

    // ✅ Bill generate karo
    public BillDTO generateBill(Integer bookingId) {
        List<FoodOrder> foodOrders = foodOrderRepository.findByBookingId(bookingId);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Booking not found with id: " + bookingId));

        Room room = booking.getRoom();
        Long totalNights = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());

        // ✅ Room subtotal
        Double roomSubtotal = room.getPrice() * totalNights;

        // ✅ Food DTOs + total
        List<FoodOrderResponseDTO> foodOrderDTOs = foodOrders.stream()
                .map(FoodOrderMapper::toResponse)
                .collect(Collectors.toList());

        Double foodTotal = foodOrders.stream()
                .mapToDouble(FoodOrder::getTotalPrice)
                .sum();

        // ✅ Final amounts
        Double subtotal = roomSubtotal + foodTotal;
        Double tax = subtotal * 0.10;
        Double totalAmount = subtotal + tax;

        Payment payment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found for booking: " + bookingId));

        BillDTO bill = new BillDTO();
        bill.setBookingId(booking.getId());
        bill.setUserName(booking.getUser().getName());
        bill.setUserEmail(booking.getUser().getEmail());
        bill.setUserPhone(booking.getUser().getPhone());
        bill.setRoomNumber(room.getRoomNumber());
        bill.setRoomType(room.getType());
        bill.setPricePerNight(room.getPrice());
        bill.setCheckIn(booking.getCheckIn());
        bill.setCheckOut(booking.getCheckOut());
        bill.setTotalNights(totalNights);
        bill.setRoomSubtotal(roomSubtotal);
        bill.setFoodOrders(foodOrderDTOs);
        bill.setFoodTotal(foodTotal);
        bill.setSubtotal(subtotal);
        bill.setTax(tax);
        bill.setTotalAmount(totalAmount);
        bill.setPaymentMethod(payment.getPaymentMethod());
        bill.setPaymentStatus(payment.getStatus());
        bill.setGeneratedAt(LocalDateTime.now());

        return bill;
    }

    public PaymentResponseDTO getPaymentByBooking(Integer bookingId) {
        Payment payment = paymentRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found for booking id: " + bookingId));
        return PaymentMapper.toResponse(payment);
    }
}