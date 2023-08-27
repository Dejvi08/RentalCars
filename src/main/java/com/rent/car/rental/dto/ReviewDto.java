package com.rent.car.rental.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private long car_id;
    private String description;
    private int rate;

}
