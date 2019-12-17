package com.asys1920.carservice.adapter;

import com.asys1920.carservice.model.Car;
import com.asys1920.carservice.model.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car carDTOtoCar(CarDTO carDTO);

    CarDTO carToCarDTO(Car car);
}
