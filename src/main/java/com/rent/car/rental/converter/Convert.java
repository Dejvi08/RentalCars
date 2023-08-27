package com.rent.car.rental.converter;

import com.rent.car.rental.dto.CarDto;
import com.rent.car.rental.dto.ResponseDto;
import com.rent.car.rental.dto.ReviewDto;
import com.rent.car.rental.entity.CarEntity;
import com.rent.car.rental.entity.ReviewEntity;

public class Convert {

    public static ResponseDto mapToDto(CarEntity carEntity) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setId(carEntity.getId());
        responseDto.setPrice(carEntity.getPrice());
        responseDto.setModel(carEntity.getModel());
        responseDto.setYear(carEntity.getYear());
        responseDto.setColor(carEntity.getColor());
        responseDto.setTransmission(carEntity.getTransmission());
        responseDto.setFuelType(carEntity.getFuelType());

        return responseDto;
    }

    public static CarEntity mapToEntity(CarDto carDto) {
        CarEntity carEntity = new CarEntity();
        carEntity.setId(carDto.getId());
        carEntity.setPrice(carDto.getPrice());
        carEntity.setModel(carDto.getModel());
        carEntity.setYear(carDto.getYear());
        carEntity.setColor(carDto.getColor());
        carEntity.setTransmission(carDto.getTransmission());
        carEntity.setFuelType(carDto.getFuelType());

        return carEntity;
    }


    public static ReviewEntity mapToEntity(ReviewDto reviewDto){
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setRate(reviewDto.getRate());
        reviewEntity.setDescription(reviewDto.getDescription());
        reviewEntity.setCarEntity(new CarEntity(reviewDto.getCar_id()));

        return reviewEntity;
    }

    public static ReviewDto mapToDto(ReviewEntity reviewEntity){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setDescription(reviewEntity.getDescription());
        reviewDto.setCar_id(reviewEntity.getId());
        reviewDto.setRate(reviewEntity.getRate());

        return reviewDto;
    }


}
