package com.asys1920.carservice.repository;

import com.asys1920.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    Car getCarById(@Param("id") String id);
    Car getCarByName(@Param("name") String name);
}
