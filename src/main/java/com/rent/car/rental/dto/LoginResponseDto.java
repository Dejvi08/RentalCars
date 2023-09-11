package com.rent.car.rental.dto;

public class LoginResponseDto extends PersonDto{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
