package com.asys1920.carservice.controller;

import com.asys1920.carservice.service.CarService;
import com.asys1920.dto.CarDTO;
import com.asys1920.mapper.CarMapper;
import com.asys1920.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/cars")
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO, BindingResult result) {
        if (result.hasErrors()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        Car carTemp = CarMapper.INSTANCE.carDTOToCar(carDTO);
        Car car = carService.createCar(carTemp);

        return new ResponseEntity<>(CarMapper.INSTANCE.carToCarDTO(car), HttpStatus.CREATED);
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable long id) {
        Car car = carService.getCar(id);
        return new ResponseEntity<>(CarMapper.INSTANCE.carToCarDTO(car), HttpStatus.OK);
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(CarMapper.INSTANCE.listCarToCarDTOs(cars), HttpStatus.OK);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
