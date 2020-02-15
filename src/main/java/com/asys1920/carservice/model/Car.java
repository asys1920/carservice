package com.asys1920.carservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Builder
@With
@NoArgsConstructor
// Benötigt, evtl. Lombok Bug siehe: https://github.com/rzwitserloot/lombok/issues/1389
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Brand is required")
    private String brand;
    @NotEmpty(message = "Model is Required")
    private String model;
    @Positive(message = "???") //TODO: Sollte ein Datum sein
    private int yearOfConstruction;
    @Positive(message = "A Car has to have at least one Seat")
    private int numberOfSeats;
    @Positive(message = "A Car has to have at least one door!")
    private int numberOfDoors;
    @Positive(message = "Renting Price must be > 0!")
    private double rentingPricePerDay; //TODO: muss nicht in Car-Objekt gespeichert werden (Business Logik)
    private VehicleType vehicleType;
    private boolean isEol;
}
