package com.asys1920.carservice.dto;

import com.asys1920.carservice.model.VehicleType;
import lombok.Data;

@Data
public class CarDTO {
    private String name;
    private String brand;
    private String model;
    private String yearOfConstruction;
    private int numberOfSeats;
    private int numberOfDoors;
    private double rentingPricePerDay;
    private VehicleType vehicleType;
}
