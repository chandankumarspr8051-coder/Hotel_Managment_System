// RoomRepository.java
package com.example.HotelmanagmentSystem.Repository;

import com.example.HotelmanagmentSystem.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByAvailable(Boolean available);
    List<Room> findByType(String type);
    boolean existsByRoomNumber(String roomNumber);
}

