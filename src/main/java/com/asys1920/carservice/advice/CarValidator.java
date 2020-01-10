package com.asys1920.carservice.advice;

import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.model.VehicleType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;

public class CarValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Car.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Car car = (Car) o;
        if (isRentingPriceInvalid(car.getRentingPricePerDay())) {
            String field = "rentingPricePerDay";
            errors.rejectValue(field, getInvalidationIndicator(field), getInvalidationMessage(field));
        }
        if (isStringInvalid(car.getBrand())) {
            String field = "brand";
            errors.rejectValue(field, getInvalidationIndicator(field), getInvalidationMessage(field));
        }
        if (isStringInvalid(car.getModel())) {
            String field = "model";
            errors.rejectValue(field, getInvalidationIndicator(field), getInvalidationMessage(field));
        }
        if (isStringInvalid(car.getName())) {
            String field = "name";
            errors.rejectValue(field, getInvalidationIndicator(field), getInvalidationMessage(field));
        }
        if (isYearOfConstructionInvalid(car.getYearOfConstruction())) {
            String field = "yearOfConstruction";
            errors.rejectValue(field, getInvalidationIndicator(field), getInvalidationMessage(field));
        }
        if (isVehicleTypeInvalid(car.getVehicleType().toString())) {
            String field = "vehicleType";
            errors.rejectValue(field, getInvalidationIndicator(field), getInvalidationMessage(field));
        }
        if (isAmountInvalid(car.getNumberOfDoors())) {
            String field = "numberOfDoors";
            errors.rejectValue(field, getInvalidationIndicator(field), getInvalidationMessage(field));
        }
        if (isAmountInvalid(car.getNumberOfSeats())) {
            String field = "numberOfSeats";
            errors.rejectValue(field, getInvalidationIndicator(field), getInvalidationMessage(field));
        }
    }

    private String getInvalidationIndicator(String field) {
        return String.format("%s.isInvalid", field);
    }

    private String getInvalidationMessage(String field) {
        return String.format("The submitted %s is not valid, please verify!", field);
    }

    private boolean isVehicleTypeInvalid(String vehicleType) {
        if (isStringInvalid(vehicleType)) {
            return true;
        }
        for (VehicleType c : VehicleType.values()) {
            if (c.name().equals(vehicleType)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRentingPriceInvalid(double rentingPrice) {
        return rentingPrice < 0.0;
    }

    private boolean isAmountInvalid(int amount) {
        return amount < 1;
    }

    private boolean isYearOfConstructionInvalid(String yearOfConstruction) {
        if (isStringInvalid(yearOfConstruction)) {
            return true;
        }
        Calendar now = Calendar.getInstance();
        if (Integer.parseInt(yearOfConstruction) > now.get(Calendar.YEAR)) {
            return true;
        }
        return false;
    }

    private boolean isStringInvalid(String s) {
        // Check if field is empty
        return s == null || s.isEmpty();
    }
}
