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
    private VehicleType vehicleType;
    private boolean isEol;

    public Car() {}
    public Car(String name) {
        this.name = name;
    }
}
