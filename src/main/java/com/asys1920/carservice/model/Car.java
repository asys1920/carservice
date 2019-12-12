package com.asys1920.carservice.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Car {
    private String id;
    private String name;
    private String brand;
    private String model;
    private String yearOfConstruction;
    private int numberOfSeats;
    private int numberOfDoors;
    private double rentingPricePerDay;
    private VehicleType vehicleType;
    private boolean isEol;

    public String getId() { return id; }
}
