package com.asys1920.carservice;

import com.asys1920.carservice.advice.CarValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class CarserviceApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CarserviceApplication.class, args);
	}

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
		v.addValidator("beforeCreate", new CarValidator());
		v.addValidator("beforeSave", new CarValidator());
	}
}
