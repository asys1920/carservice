package com.asys1920.carservice.model;

import lombok.*;
import lombok.experimental.Tolerate;

import javax.persistence.*;

@Entity
@Data
@Builder
@With
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String brand;
    private String model;
    private int yearOfConstruction;
    private int numberOfSeats;
    private int numberOfDoors;
    private double rentingPricePerDay; //TODO: muss nicht in Car-Objekt gespeichert werden (Business Logik)
    private String vehicleType;
    private boolean isEol;

    @Tolerate //TODO: Herr Schroeder fragen, wenn dies fehlt, crashed es, da default construtor fehlt
    Car(){}
}
