// PaymentRepository.java
package com.example.HotelmanagmentSystem.Repository;

import com.example.HotelmanagmentSystem.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByBookingId(Integer bookingId);
    List<Payment> findByStatus(String status);
}