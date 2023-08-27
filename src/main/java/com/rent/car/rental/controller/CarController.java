package com.rent.car.rental.controller;

import com.rent.car.rental.dto.CarDto;
import com.rent.car.rental.dto.ErrorResponse;
import com.rent.car.rental.dto.ResponseDto;
import com.rent.car.rental.service.impl.impl.CarServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car/")

public class CarController {

    @Autowired
    private CarServiceImpl carService;


    @PostMapping
    public ResponseEntity<ResponseDto> createCar(@Valid @RequestBody CarDto carDto) {
        ResponseDto response = new ResponseDto();
        try {
            response = carService.createCar(carDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorMessage(e.getLocalizedMessage());
            errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setErrorResponse(errorResponse);

            return new ResponseEntity<>(carService.createCar(carDto), HttpStatus.CREATED);


        }


    }


    @GetMapping("{id}")
    public CarDto getCar(@PathVariable("id") Long Id) {
        return carService.getCarById(Id);
    }

    @GetMapping("/all")

   public List<CarDto>getAllCars(){
        return carService.getAllCars();

    }

    @PutMapping("{id}")
    public CarDto updateCar(@RequestBody CarDto carDto,@PathVariable("id") Long Id){
        return carService.updateCar(carDto, Id);
    }

@DeleteMapping("{id}")
    public String deleteCarById(@PathVariable("id") Long Id){
        carService.deleteCarById(Id);

        return "CAR IS SUCCESSFULLY DELETED";
    }

}
