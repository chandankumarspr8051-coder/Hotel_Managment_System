
// BookingRepository.java
package com.example.HotelmanagmentSystem.Repository;

import com.example.HotelmanagmentSystem.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUserId(Integer userId);
    List<Booking> findByRoomId(Integer roomId);
    List<Booking> findByStatus(String status);
}