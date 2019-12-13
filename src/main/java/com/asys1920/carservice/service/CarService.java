package com.asys1920.carservice.service;

import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public void createCar(Car car) {
        carRepository.save(car);
    }

    public Car getCarById(String id) {
        return carRepository.getCarById(id);
    }

    public Car getCarByName(String name) { return carRepository.getCarByName(name); }

}