package com.rent.car.rental.service.impl.impl;

import com.rent.car.rental.converter.Convert;
import com.rent.car.rental.dto.CarDto;
import com.rent.car.rental.dto.ResponseDto;
import com.rent.car.rental.entity.CarEntity;
import com.rent.car.rental.exeption.ResourceNotFoundExeption;
import com.rent.car.rental.repository.CarRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Service
public class CarServiceImpl {

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public ResponseDto createCar(CarDto carDto) {
        CarEntity carEntity = Convert.mapToEntity(carDto);
        CarEntity newCarEntity = carRepository.save(carEntity);
        return Convert.mapToDto(newCarEntity);
    }


    public CarDto getCarById(Long carId) {
        CarEntity carEntity = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundExeption("CarEntity", "id",
                carId));

return Convert.mapToDto(carEntity);
    }


    public List<CarDto> getAllCars() {
        List<CarEntity>cars = carRepository.findAll();
        return  cars.stream().map(car -> Convert.mapToDto(car)).collect(Collectors.toList());
    }


    public CarDto updateCar(CarDto carDto, Long Id) {
        CarEntity carEntity = carRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundExeption("CarEntity", "Id",
                Id));
        carEntity.setFuelType(carDto.getFuelType());
        carEntity.setModel(carDto.getModel());
        carEntity.setYear(carDto.getYear());
        carEntity.setColor(carDto.getColor());
        carEntity.setTransmission(carDto.getTransmission());
        carEntity.setPrice(carDto.getPrice());

        CarEntity updateCar = carRepository.save(carEntity);

        return Convert.mapToDto(updateCar);

    }


    public void deleteCarById(Long Id) {
        CarEntity carEntity = carRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundExeption("CarEntity", "Id",
                Id));
        carRepository.delete(carEntity);
    }


}
