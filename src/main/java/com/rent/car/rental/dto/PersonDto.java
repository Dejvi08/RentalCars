package com.rent.car.rental.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PersonDto {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String username;

    private int CreditCardNumber;
    private String password;
}
