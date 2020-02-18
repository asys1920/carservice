package com.asys1920.carservice.controller;

import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String name;
    private String brand;
    private String model;
    private int yearOfConstruction;
    private int numberOfSeats;
    private int numberOfDoors;
    private double rentingPricePerDay;
}
