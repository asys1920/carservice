package com.asys1920.carservice.controller;

import com.asys1920.carservice.dto.CarDTO;
import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarServiceController {
    @Autowired
    private CarService carService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public String createCar(@RequestBody CarDTO carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        carService.createCar(car);
        return "Car successfully created!";
    }

}
