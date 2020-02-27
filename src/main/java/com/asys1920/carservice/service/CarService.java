package com.asys1920.carservice.service;

import com.asys1920.carservice.repository.CarRepository;
import com.asys1920.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public boolean carExists(Car car) {
        Optional<Car> foundCar = carRepository.findByName(car.getName());
        return foundCar.isPresent();
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
