package com.asys1920.carservice.controller;

import com.asys1920.carservice.dto.CarDto;
import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarServiceController {
    @Autowired
    private CarService carService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseBody
    public ResponseEntity createCar(@RequestBody CarDto carDto) {
        Car car = modelMapper.map(carDto, Car.class);
        carService.createCar(car);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
