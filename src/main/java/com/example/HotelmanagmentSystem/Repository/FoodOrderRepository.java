package com.example.HotelmanagmentSystem.Repository;

import com.example.HotelmanagmentSystem.Entity.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Integer> {
    List<FoodOrder> findByBookingId(Integer bookingId);
}
