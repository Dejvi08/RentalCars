package com.rent.car.rental.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingDto {
    private Long id;
    private Date bookingDate;
    private String carModel;
    private int quantity;
    private String firstName;
    private String lastName;

}
