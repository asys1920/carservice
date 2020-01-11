package com.asys1920.carservice.repository;

import com.asys1920.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "car", path = "car")
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findCarByName(String name);
    List<Car> findCarsByBrand(String brand);

}
