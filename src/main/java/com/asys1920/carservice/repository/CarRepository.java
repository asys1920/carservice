package com.asys1920.carservice.repository;

import com.asys1920.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "car", path = "car")
public interface CarRepository extends JpaRepository<Car, String> {

    Car findCarById(@Param("id") Long id);
    Car findCarByName(@Param("name") String name);
    List<Car> findAll();

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void delete(Car car);

}
