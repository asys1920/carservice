package com.asys1920.carservice.controller;

import com.asys1920.carservice.adapter.CarMapper;
import com.asys1920.carservice.exceptions.ValidationException;
import com.asys1920.carservice.model.CarDTO;
import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cars")
public class CarServiceController {
    private final CarService carService;


    // Using constructor-based dependency injection
    @Autowired
    public CarServiceController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) throws ValidationException {
        Set<ConstraintViolation<CarDTO>> validate = Validation.buildDefaultValidatorFactory().getValidator().validate(carDTO);
    if(!validate.isEmpty()) {
        throw new ValidationException(validate);
    }

    Car car = carService.createCar(CarMapper.INSTANCE.carDTOtoCar(carDTO));
    CarDTO responseDTO = CarMapper.INSTANCE.carToCarDTO(car);
    return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CarDTO> getAllCars() {

        List<Car> allCars = carService.getAllCars();
        /*
        return new ResponseEntity<CarDTO>(allCars.stream()
                .map(this::carDTOtoCar)
                .collect(Collectors.toList()), HttpStatus.OK);

    */
        return null;
    }


}
