package com.asys1920.carservice.repository;

import com.asys1920.carservice.model.Car;

import java.util.HashMap;
import java.util.Map;

public class CarRepository {
    private Map<String, Car> hashMap = new HashMap<String, Car>();

    public Car getCarById(String id) {
        return hashMap.get(id);
    }

    public void addCar(Car car) {
        hashMap.put(car.getId(), car);
    }
}
