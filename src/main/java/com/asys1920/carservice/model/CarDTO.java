package com.asys1920.carservice.model;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CarDTO {
    @NotNull(message = "Car name cannot be empty!")
    private String name;
    private String brand;
    private String model;
    private String yearOfConstruction;
    private int numberOfSeats;
    private int numberOfDoors;
    private double rentingPricePerDay;
    private VehicleType vehicleType;
}
