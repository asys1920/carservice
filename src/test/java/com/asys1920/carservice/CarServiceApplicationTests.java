package com.asys1920.carservice;

import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CarServiceApplicationTests {
	@Autowired
	CarService carService;
	@Test
	void contextLoads() {
	}

	@Test
	void testCreateCarEverythingEmpty() {

	}

	@Test
	void testCreateCarSimple(){
		String name = "TestAuto2";
		Car newCar = new Car(name);
		carService.createCar(newCar);

		Car retrievedCar = carService.getCarByName(name);
		Assertions.assertEquals(newCar.getName(), retrievedCar.getName());
	}
}