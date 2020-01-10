package com.asys1920.carservice.repository;

import com.asys1920.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "car", path = "car")
public interface CarRepository extends JpaRepository<Car, Long> {

    Car getCarById(@Param("id") Long id);
    Car getCarByName(@Param("name") String name);
}
