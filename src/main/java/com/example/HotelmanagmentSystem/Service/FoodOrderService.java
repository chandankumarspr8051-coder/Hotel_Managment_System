package com.example.HotelmanagmentSystem.Service;

import com.example.HotelmanagmentSystem.DTO.FoodOrderRequestDTO;
import com.example.HotelmanagmentSystem.DTO.FoodOrderResponseDTO;
import com.example.HotelmanagmentSystem.Entity.Booking;
import com.example.HotelmanagmentSystem.Entity.FoodMenu;
import com.example.HotelmanagmentSystem.Entity.FoodOrder;
import com.example.HotelmanagmentSystem.Mapper.FoodOrderMapper;
import com.example.HotelmanagmentSystem.Repository.BookingRepository;
import com.example.HotelmanagmentSystem.Repository.FoodMenuRepository;
import com.example.HotelmanagmentSystem.Repository.FoodOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodOrderService {

    private final FoodOrderRepository foodOrderRepository;
    private final BookingRepository bookingRepository;
    private final FoodMenuRepository foodMenuRepository;

    public FoodOrderResponseDTO createOrder(FoodOrderRequestDTO requestDTO) {
        Booking booking = bookingRepository.findById(requestDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found!"));

        FoodMenu menu = foodMenuRepository.findById(requestDTO.getFoodMenuId())
                .orElseThrow(() -> new RuntimeException("Food item not found!"));

        Double totalPrice = menu.getPrice() * requestDTO.getQuantity();

        FoodOrder order = new FoodOrder();
        order.setBooking(booking);
        order.setFoodMenu(menu);
        order.setQuantity(requestDTO.getQuantity());
        order.setTotalPrice(totalPrice);
        order.setOrderedAt(LocalDateTime.now());

        return FoodOrderMapper.toResponse(foodOrderRepository.save(order));
    }

    public List<FoodOrderResponseDTO> getOrdersByBooking(Integer bookingId) {
        return foodOrderRepository.findByBookingId(bookingId)
                .stream()
                .map(FoodOrderMapper::toResponse)
                .collect(Collectors.toList());
    }
}
