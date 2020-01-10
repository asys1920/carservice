package com.asys1920.carservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String brand;
    private String model;
    private String yearOfConstruction;
    private int numberOfSeats;
    private int numberOfDoors;
    private double rentingPricePerDay;
    private String vehicleType;
    private boolean isEol;

    public Car() {
        name = brand = model = yearOfConstruction = vehicleType = "";
        numberOfDoors = numberOfSeats = 0;
        rentingPricePerDay = 0.0;
        isEol = false;
    }
    public Car(String name) {
        this.name = name;
    }
}
