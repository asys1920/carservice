package com.asys1920.carservice.controller;

import com.asys1920.carservice.exceptions.CarAlreadyExistsException;
import com.asys1920.carservice.exceptions.IllegalVehicleTypeException;
import com.asys1920.carservice.exceptions.ValidationException;
import com.asys1920.carservice.service.CarService;
import com.asys1920.dto.CarDTO;
import com.asys1920.dto.UserDTO;
import com.asys1920.mapper.CarMapper;
import com.asys1920.model.Car;
import com.asys1920.model.VehicleType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.List;
import java.util.Set;

@RestController
public class CarController {

    private static final String PATH = "/cars";
    private final CarService carService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);


    public CarController(CarService carService) {
        this.carService = carService;
    }

    private void validateCarDTO(CarDTO carDTO) throws ValidationException {
        Set<ConstraintViolation<CarDTO>> validate = Validation.buildDefaultValidatorFactory().getValidator().validate(carDTO);
        if (!validate.isEmpty()) {
            throw new ValidationException(validate);
        }
    }

    @ApiOperation(value = "Create a new car", response = CarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created car"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @PostMapping(PATH)
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) throws ValidationException, IllegalVehicleTypeException, CarAlreadyExistsException {
        validateCarDTO(carDTO);


        if (!VehicleType.contains(carDTO.getVehicleType())) {
            throw new IllegalVehicleTypeException();
        }

        Car carTemp = CarMapper.INSTANCE.carDTOToCar(carDTO);
        if (carService.carExists(carTemp)) {
            throw new CarAlreadyExistsException();
        }

        Car car = carService.createCar(carTemp);

        return new ResponseEntity<>(CarMapper.INSTANCE.carToCarDTO(car), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get a existing car", response = CarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched car"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @GetMapping(PATH + "/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable long id) {
        Car car = carService.getCar(id);
        return new ResponseEntity<>(CarMapper.INSTANCE.carToCarDTO(car), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all existing cars", response = CarDTO.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched cars"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @GetMapping(PATH)
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(CarMapper.INSTANCE.listCarToCarDTOs(cars), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a existing car", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
