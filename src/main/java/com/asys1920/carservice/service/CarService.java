package com.asys1920.carservice.service;

import com.asys1920.carservice.repository.CarRepository;
import com.asys1920.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarService {
    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public boolean carExists(Car car) {
        LOG.trace(String.format("SERVICE %s %d initiated", "carExists", car.getId()));
        Optional<Car> foundCar = carRepository.findByName(car.getName());
        return foundCar.isPresent();
    }

    public Car createCar(Car car) {
        LOG.trace(String.format("SERVICE %s %d initiated", "createCar",car.getId()));
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        LOG.trace(String.format("SERVICE %s initiated", "getAllCars"));
        return carRepository.findAll();
    }

    public Car getCar(long id) {
        LOG.trace(String.format("SERVICE %s %d initiated", "getCar",id));
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent()){
            return car.get();
        }else{
            throw new NoSuchElementException(String.format("No car with if %d is known", id));
        }
    }

    public void deleteCar(long id) {
        LOG.trace(String.format("SERVICE %s %d initiated", "deleteCar",id));
        carRepository.deleteById(id);
    }
}
