package com.asys1920.carservice;


import com.asys1920.carservice.repository.CarRepository;
import com.asys1920.model.Car;
import com.asys1920.model.VehicleType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = CarServiceApplication.class)
@AutoConfigureMockMvc
class CarServiceApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void should_ReturnValidCar_When_RequestingValidId() throws Exception {
        Car car = createdCar();
        mockMvc.perform(get("/cars/" + car.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(car.getName()))
                .andExpect(jsonPath("$.brand").value(car.getBrand()))
                .andExpect(jsonPath("$.model").value(car.getModel()))
                .andExpect(jsonPath("$.yearOfConstruction").value(car.getYearOfConstruction()))
                .andExpect(jsonPath("$.numberOfDoors").value(car.getNumberOfDoors()))
                .andExpect(jsonPath("$.numberOfSeats").value(car.getNumberOfSeats()))
                .andExpect(jsonPath("$.carBaseRentPrice").value(car.getCarBaseRentPrice()))
                .andExpect(jsonPath("$.vehicleType").value(car.getVehicleType().toString()));
    }

    @Test
    void should_ReturnErrorMessage_When_RequestingNotExistingCar() throws Exception {
        mockMvc.perform(get("/cars/" + getRandomId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_ReturnValidCar_When_CreatingValidCar() throws Exception {
        JSONObject body = getValidCar();

        MvcResult result = mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(body.get("name")))
                .andExpect(jsonPath("$.brand").value(body.get("brand")))
                .andExpect(jsonPath("$.model").value(body.get("model")))
                .andExpect(jsonPath("$.yearOfConstruction").value(body.getInt("yearOfConstruction")))
                .andExpect(jsonPath("$.numberOfDoors").value(body.getInt("numberOfDoors")))
                .andExpect(jsonPath("$.numberOfSeats").value(body.getInt("numberOfSeats")))
                .andExpect(jsonPath("$.carBaseRentPrice").value(body.getDouble("carBaseRentPrice")))
                .andExpect(jsonPath("$.vehicleType").value(body.get("vehicleType")))
                .andReturn();
    }

    @Test
    public void should_ReturnErrorMessage_When_CreatingInvalidCar_NameMissing() throws Exception {
        JSONObject body = getValidCar();
        body.put("name", null);
        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_ReturnErrorMessage_When_CreatingInvalidCar_BrandMissing() throws Exception {
        JSONObject body = getValidCar();
        body.put("brand", null);
        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_ReturnErrorMessage_When_CreatingInvalidCar_ModelMissing() throws Exception {
        JSONObject body = getValidCar();
        body.put("model", null);
        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_ReturnErrorMessage_When_CreatingInvalidCar_YearOfConstructionNegative() throws Exception {
        JSONObject body = getValidCar();
        body.put("yearOfConstruction", -1);
        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_ReturnErrorMessage_When_CreatingInvalidCar_CarBaseRentPriceNegative() throws Exception {
        JSONObject body = getValidCar();
        body.put("carBaseRentPrice", -1.0);
        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_ReturnErrorMessage_When_CreatingInvalidCar_NumberOfDoorsNegative() throws Exception {
        JSONObject body = getValidCar();
        body.put("numberOfDoors", -1);
        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_ReturnErrorMessage_When_CreatingInvalidCar_NumberOfSeatsNegative() throws Exception {
        JSONObject body = getValidCar();
        body.put("numberOfSeats", -1);
        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_ReturnErrorMessage_When_CreatingInvalidCar_UnknownVehicleType() throws Exception {
        JSONObject body = getValidCar();
        body.put("vehicleType", "garbage");
        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnErrorMessage_When_CreatingCar_NameAlreadyExists() throws Exception {
        JSONObject body = getValidCar();
        body.put("name", "TestCar2");

        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldReturnErrorMessage_When_DeletingCar_IdNotFound() throws Exception {
        mockMvc.perform(delete("/cars/" + getRandomId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnOk_When_DeletingCar() throws Exception {
        JSONObject car = getValidCar();
        car.put("id", 150);
        car.put("name", "TestCar3");

        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(car.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/cars/" + 150)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnOk_WhenUpdatingCar() throws Exception {
        JSONObject car = getValidCar();
        car.put("id", 100);
        car.put("name", "TestCar4");

        mockMvc.perform(post("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(car.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        car.put("brand", "MyFakeBrand");

        mockMvc.perform(patch("/cars/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(car.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private JSONObject getValidCar() throws JSONException {
        JSONObject body = new JSONObject();
        body.put("name", "TestCar");
        body.put("brand", "TestBrand");
        body.put("model", "TestModel");
        body.put("yearOfConstruction", 2010);
        body.put("numberOfDoors", 4);
        body.put("numberOfSeats", 4);
        body.put("carBaseRentPrice", 10.0);
        body.put("vehicleType", "SUV");
        return body;
    }

    private int getRandomId() {
        return (int) (Math.random() * Integer.MAX_VALUE);
    }


    private Car createdCar() throws JSONException {
        Car car = new Car();
        JSONObject validCar = getValidCar();
        car.setName(validCar.getString("name"));
        car.setBrand(validCar.getString("brand"));
        car.setModel(validCar.getString("model"));
        car.setYearOfConstruction(validCar.getInt("yearOfConstruction"));
        car.setNumberOfDoors(validCar.getInt("numberOfDoors"));
        car.setNumberOfSeats(validCar.getInt("numberOfSeats"));
        car.setCarBaseRentPrice(validCar.getDouble("carBaseRentPrice"));
        car.setVehicleType(VehicleType.get(validCar.getString("vehicleType")));
        carRepository.save(car);
        return car;
    }

}
