package com.asys1920.carservice.repository;

import com.asys1920.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByName(String name);
}
