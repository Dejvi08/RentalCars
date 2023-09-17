package com.rent.car.rental.exeption;

import org.springframework.http.HttpStatus;

public class BlogApiExeption extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogApiExeption(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public BlogApiExeption(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
