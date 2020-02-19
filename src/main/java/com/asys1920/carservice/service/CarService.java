package com.asys1920.carservice.service;

import com.asys1920.carservice.repository.CarRepository;
import com.asys1920.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCar(long id) {
        return carRepository.findById(id).get();
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }
}
