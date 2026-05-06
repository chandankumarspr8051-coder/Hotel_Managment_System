// BookingService.java
package com.example.HotelmanagmentSystem.Service;

import com.example.HotelmanagmentSystem.DTO.BookingRequestDTO;
import com.example.HotelmanagmentSystem.DTO.BookingResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Booking;
import com.example.HotelmanagmentSystem.Entity.Room;
import com.example.HotelmanagmentSystem.Entity.User;
import com.example.HotelmanagmentSystem.Mapper.BookingMapper;
import com.example.HotelmanagmentSystem.Repository.BookingRepository;
import com.example.HotelmanagmentSystem.Repository.RoomRepository;
import com.example.HotelmanagmentSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private EmailService emailService;



    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(BookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BookingResponseDTO getBookingById(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return BookingMapper.toResponse(booking);
    }

    public List<BookingResponseDTO> getBookingsByUser(Integer userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(BookingMapper::toResponse)
                .collect(Collectors.toList());
    }

    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Room room = roomRepository.findById(requestDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found!"));

        if (!room.getAvailable()) {
            throw new RuntimeException("Room is not available!");
        }

        // ✅ Tax calculate kar
        Double subtotal = requestDTO.getRoomPrice() * requestDTO.getNights();
        Double tax = subtotal * 0.10;  // 10% tax
        Double totalPrice = subtotal + tax;  // ← FINAL AMOUNT

        // Room unavailable karo
        room.setAvailable(false);
        roomRepository.save(room);

        Booking booking = new Booking();
        booking.setCheckIn(requestDTO.getCheckIn());
        booking.setCheckOut(requestDTO.getCheckOut());
        booking.setTotalPrice(requestDTO.getTotalPrice());
        booking.setTotalPrice(totalPrice);
        booking.setStatus("PENDING");
        booking.setUser(user);
        booking.setRoom(room);

        Booking savedBooking = bookingRepository.save(booking);

        emailService.sendBookingMail(
                user.getEmail(),
                user.getName(),
                room.getRoomNumber()
        );

        return BookingMapper.toResponse(bookingRepository.save(booking));
    }

    public BookingResponseDTO updateStatus(Integer id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        booking.setStatus(status);

        if ("CANCELLED".equals(status)) {
            Room room = booking.getRoom();
            room.setAvailable(true);
            roomRepository.save(room);
        }
        return BookingMapper.toResponse(bookingRepository.save(booking));
    }

    public void deleteBooking(Integer id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }
}