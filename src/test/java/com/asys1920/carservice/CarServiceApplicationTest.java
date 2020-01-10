package com.asys1920.carservice;

import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.repository.CarRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CarServiceApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void should_ReturnValid_When_Get_ValidRequest() throws Exception {
        mockMvc.perform(get("/api/car"))
                .andExpect(status().isOk());
    }


    @Test
    public void should_ReturnErrorMessage_When_Post_InvalidRentingPrice() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "VW Golf 6");
        body.put("brand", "VW");
        body.put("model", "Golf 6");
        body.put("yearOfConstruction", "2012");
        body.put("numberOfSeats", "5");
        body.put("vehicleType", "SALOON");
        body.put("rentingPricePerDay", "-5");
        body.put("isEol", "false");
        mockMvc.perform(post("/api/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void should_ReturnErrorMessage_When_Post_InvalidVehicleType() throws Exception {
        JSONObject body = new JSONObject();
        body.put("name", "VW Golf 6");
        body.put("brand", "VW");
        body.put("model", "Golf 6");
        body.put("yearOfConstruction", "2012");
        body.put("numberOfSeats", "5");
        body.put("vehicleType", "falscherTyp");
        body.put("rentingPricePerDay", "5");
        body.put("isEol", "false");
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
        body.put("numberOfSeats", "5");
        body.put("vehicleType", "SALOON");
        body.put("rentingPricePerDay", "5");
        body.put("isEol", "false");
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
                .andExpect(jsonPath("$.numberOfSeats").value(body.get("numberOfSeats")))
                .andExpect(jsonPath("$.vehicleType").value(body.get("vehicleType")))
                .andExpect(jsonPath("$.rentingPricePerDay").value(body.get("rentingPricePerDay")))
                .andExpect(jsonPath("$.isEol").value(body.get("isEol")));
    }

    @Test
    public void should_GetUser_When_Post_ValidRequest() throws Exception {
        Car c = new Car();
        c.setName("VW Golf 6");
        c.setBrand("VW");
        c.setModel("Golf 6");
        c.setYearOfConstruction("2012");
        c.setNumberOfSeats(5);
        c.setVehicleType("SALOON");
        c.setRentingPricePerDay(5.0);
        c.setEol(false);
        carRepository.save(c);

        mockMvc.perform(get("/api/car/" + c.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("VW Golf 6"))
                .andExpect(jsonPath("$.brand").value("VW"))
                .andExpect(jsonPath("$.model").value("Golf 6"))
                .andExpect(jsonPath("$.yearOfConstruction").value("2012"))
                .andExpect(jsonPath("$.numberOfSeats").value("5"))
                .andExpect(jsonPath("$.vehicleType").value("SALOON"))
                .andExpect(jsonPath("$.rentingPricePerDay").value("5.0"))
                .andExpect(jsonPath("$.isEol").value("false"));
    }



}
