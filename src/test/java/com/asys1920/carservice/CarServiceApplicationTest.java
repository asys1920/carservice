package com.asys1920.carservice;


import com.asys1920.carservice.repository.CarRepository;
import com.asys1920.model.Car;
import com.asys1920.model.VehicleType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = CarServiceApplication.class)
@AutoConfigureMockMvc
class CarServiceApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void should_ReturnValid_When_Get_ValidRequest() throws Exception {
        mockMvc.perform(get("/api/car"))
                .andExpect(status().isOk()); //TODO: pruefen auf response-body (leere Liste)
    }

    @Test
    //TODO: parameterisierter Test
    public void should_ReturnErrorMessage_When_Post_InvalidRentingPrice() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "VW Golf 6");
        body.put("brand", "VW");
        body.put("model", "Golf 6");
        body.put("yearOfConstruction", "2012");
        body.put("numberOfDoors", "5");
        body.put("numberOfSeats", "5");
        body.put("vehicleType", "SALOON");
        body.put("rentingPricePerDay", "-5.0"); // is invalid
        body.put("eol", "false");
        mockMvc.perform(post("/api/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); //TODO: body pruefen (VALIDATION ERROR)
    }


    @Test
    public void should_ReturnErrorMessage_When_Post_InvalidVehicleType() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "VW Golf 6");
        body.put("brand", "VW");
        body.put("model", "Golf 6");
        body.put("yearOfConstruction", "2012");
        body.put("numberOfDoors", "5");
        body.put("numberOfSeats", "5");
        body.put("vehicleType", "falscherTyp"); // is invalid
        body.put("rentingPricePerDay", "5.0");
        body.put("eol", "false");
        mockMvc.perform(post("/api/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_CreateUser_When_Post_ValidRequest() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "VW Golf 6");
        body.put("brand", "VW");
        body.put("model", "Golf 6");
        body.put("yearOfConstruction", "2012");
        body.put("numberOfDoors", "5");
        body.put("numberOfSeats", "5");
        body.put("vehicleType", "SALOON");
        body.put("rentingPricePerDay", "5.0");
        body.put("eol", "false");
        mockMvc.perform(post("/api/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(body.get("name")))
                .andExpect(jsonPath("$.brand").value(body.get("brand")))
                .andExpect(jsonPath("$.model").value(body.get("model")))
                .andExpect(jsonPath("$.yearOfConstruction").value(body.get("yearOfConstruction")))
                .andExpect(jsonPath("$.numberOfDoors").value(body.get("numberOfDoors")))
                .andExpect(jsonPath("$.numberOfSeats").value(body.get("numberOfSeats")))
                .andExpect(jsonPath("$.vehicleType").value(body.get("vehicleType")))
                .andExpect(jsonPath("$.rentingPricePerDay").value(body.get("rentingPricePerDay")))
                .andExpect(jsonPath("$.eol").value(body.get("eol")));
    }

    //TODO: Give Tests fitting Names
    //TODO: Fix this Test:
    @Test
    public void should_GetUser_When_Post_ValidRequest() throws Exception {
        Car car = Car.builder()
                .name("VW Golf 6")
                .brand("VW")
                .model("Golf 6")
                .yearOfConstruction(2012)
                .numberOfDoors(5)
                .numberOfSeats(5)
                .vehicleType(VehicleType.SALOON)
                .rentingPricePerDay(5.0)
                .isEol(true)
                .build();
        // Car car2 = car.withBrand("Bla"); //TODO: @With-Example entfernen
        carRepository.save(car);

        mockMvc.perform(get("/api/car/" + car.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(car.getName()))
                .andExpect(jsonPath("$.brand").value(car.getBrand()))
                .andExpect(jsonPath("$.model").value(car.getModel()))
                .andExpect(jsonPath("$.yearOfConstruction").value(car.getYearOfConstruction()))
                .andExpect(jsonPath("$.numberOfDoors").value(car.getNumberOfDoors()))
                .andExpect(jsonPath("$.numberOfSeats").value(car.getNumberOfSeats()))
                .andExpect(jsonPath("$.vehicleType").value(car.getVehicleType().name()))
                .andExpect(jsonPath("$.rentingPricePerDay").value(car.getRentingPricePerDay()))
                .andExpect(jsonPath("$.eol").value(car.isEol()));
    }



}
