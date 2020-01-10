package com.asys1920.carservice.advice;

import com.asys1920.carservice.model.Car;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CarValidatorTest {
    CarValidator carValidator;

    @BeforeAll()
    void setup() {
        carValidator = new CarValidator();
    }


}
