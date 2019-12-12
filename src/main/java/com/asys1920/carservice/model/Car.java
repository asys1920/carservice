package com.asys1920.carservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Setter
@Getter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
