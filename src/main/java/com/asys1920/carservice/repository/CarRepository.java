package com.asys1920.carservice.repository;

import com.asys1920.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
}
