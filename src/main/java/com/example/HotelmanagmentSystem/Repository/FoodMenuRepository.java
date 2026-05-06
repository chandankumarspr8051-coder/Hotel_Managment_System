package com.example.HotelmanagmentSystem.Repository;

import com.example.HotelmanagmentSystem.Entity.FoodMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenu, Integer> {
    List<FoodMenu> findAll();
}
