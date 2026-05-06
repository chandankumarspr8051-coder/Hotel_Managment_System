// StaffRepository.java
package com.example.HotelmanagmentSystem.Repository;

import com.example.HotelmanagmentSystem.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    List<Staff> findByPosition(String position);
    boolean existsByEmail(String email);
}