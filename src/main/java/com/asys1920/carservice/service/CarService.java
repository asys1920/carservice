package com.asys1920.carservice.service;

import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(Long id) {
        return carRepository.findCarById(id);
    }

    public List<Car> getAllCars() { return carRepository.findAll(); }

    public Car getCarByName(String name) { return carRepository.findCarByName(name); }

}