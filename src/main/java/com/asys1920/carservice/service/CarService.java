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

    /**
     * Checks if the given car exists in the repository
     * @param car the car that gets checked
     * @return if the car is in the repository
     */
    public boolean carExists(Car car) {
        LOG.trace(String.format("SERVICE %s %d initiated", "carExists", car.getId()));
        Optional<Car> foundCar = carRepository.findByName(car.getName());
        return foundCar.isPresent();
    }

    /**
     * Saves a car in the repository
     * @param car the car that gets saved
     * @return the saved car
     */
    public Car createCar(Car car) {
        LOG.trace(String.format("SERVICE %s %d initiated", "createCar",car.getId()));
        return carRepository.save(car);
    }

    /**
     * Retrieves all cars from the repository
     * @return a list with all cars in the repository
     */
    public List<Car> getAllCars() {
        LOG.trace(String.format("SERVICE %s initiated", "getAllCars"));
        return carRepository.findAll();
    }

    /**
     * Gets a car associated with the given id
     * @param id the id of the car
     * @return the car
     * @throws NoSuchElementException gets thrown when there is no car known for the given id
     */
    public Car getCar(long id) {
        LOG.trace(String.format("SERVICE %s %d initiated", "getCar",id));
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent()){
            return car.get();
        }else{
            throw new NoSuchElementException(String.format("No car with if %d is known", id));
        }
    }

    /**
     * Deletes a car from the repository
     * @param id the id of the car that gets deleted
     * @throws NoSuchElementException gets thrown when there is no car known with the given id
     */
    public void deleteCar(long id) {
        LOG.trace(String.format("SERVICE %s %d initiated", "deleteCar",id));
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent()){
            carRepository.delete(car.get());
        }else{
            throw new NoSuchElementException(String.format("No car with if %d is known", id));
        }
    }
}
