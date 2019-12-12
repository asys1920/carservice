package com.asys1920.carservice.services;

import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private static CarRepository carRepository;

    public void createCar(Car car) {
        carRepository.addCar(car);
    }


}