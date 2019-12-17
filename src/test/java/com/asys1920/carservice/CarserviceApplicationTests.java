package com.asys1920.carservice;

import com.asys1920.carservice.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarserviceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testCreateCar() throws Exception {
		Car car = new Car();

		mockMvc.perform(post("/carservice/create/")
				.contentType("application/json")
				.param("X", "Y")
				.content(objectMapper.writeValueAsString(car)))
				.andExpect(status().isOk());

	}
	@Test
	void contextLoads() {
	}

	@Test
	void testCreateCarEverythingEmpty() {

	}
}
