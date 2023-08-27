package com.rent.car.rental.dto;

import lombok.Data;

@Data
public class CarDto {
private Long id;
private String model;

private String color;

private int year;

private int price;

private String fuelType;

private String transmission;
}
