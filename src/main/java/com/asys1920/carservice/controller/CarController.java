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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.List;
import java.util.Set;

@RestController
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);
    private static final String PATH = "/cars";
    private final CarService carService;


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
        LOG.trace(String.format("DELETE %s/%d initiated", PATH, carDTO.getId()));
        if (!VehicleType.contains(carDTO.getVehicleType())) {
            throw new IllegalVehicleTypeException();
        }

        Car carTemp = CarMapper.INSTANCE.carDTOToCar(carDTO);
        if (carService.carExists(carTemp)) {
            throw new CarAlreadyExistsException();
        }

        Car car = carService.createCar(carTemp);

        LOG.trace(String.format("DELETE %s/%d XYZ", PATH, carDTO.getId()));
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
        LOG.trace(String.format("GET %s/%d initiated", PATH, id));
        Car car = carService.getCar(id);
        LOG.trace(String.format("GET %s/%d completed", PATH, id));
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
        LOG.trace(String.format("GET %s initiated", PATH));
        List<Car> cars = carService.getAllCars();
        LOG.trace(String.format("GET %s completed", PATH));
        return new ResponseEntity<>(CarMapper.INSTANCE.listCarToCarDTOs(cars), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an existing car", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the user"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    @DeleteMapping(PATH + "/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable long id) {
        LOG.trace(String.format("DELETE %s/%d initiated", PATH, id));
        carService.deleteCar(id);
        LOG.trace(String.format("DELETE %s/%d completed", PATH, id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Update an existing car", response = CarDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated car"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden")
    })
    @PatchMapping(PATH)
    public ResponseEntity<CarDTO> updateCar(@RequestBody CarDTO carDTO) throws ValidationException {
        LOG.trace(String.format("PATCH %s/%d initiated", PATH, carDTO.getId()));
        validateCarDTO(carDTO);
        Car car = CarMapper.INSTANCE.carDTOToCar(carDTO);
        car = carService.createCar(car);
        LOG.trace(String.format("PATCH %s/%d completed", PATH, carDTO.getId()));
        return new ResponseEntity<>(CarMapper.INSTANCE.carToCarDTO(car), HttpStatus.OK);
    }
}
