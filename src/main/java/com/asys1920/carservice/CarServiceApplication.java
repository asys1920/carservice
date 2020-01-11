package com.asys1920.carservice;

import com.asys1920.carservice.advice.CarValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class CarServiceApplication implements RepositoryRestConfigurer {

	@Autowired
	private CarValidator carValidator;

	public static void main(String[] args) {
		SpringApplication.run(CarServiceApplication.class, args);
	}

	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		validatingListener.addValidator("beforeCreate", carValidator);
		validatingListener.addValidator("beforeSave", carValidator);
	}
}
