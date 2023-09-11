package com.rent.car.rental.controller;

import com.rent.car.rental.dto.ReviewDto;
import com.rent.car.rental.service.impl.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review/")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewService;

    @PostMapping("/cars/{carId}/review_entity")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("carId") long carId, @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(carId, reviewDto), HttpStatus.CREATED);

    }
    @GetMapping("/cars/{carId}/review_entity")
    public List<ReviewDto> getReviewsByPostId(@PathVariable(value = "carId") Long carId){
        return reviewService.getReviewsByCarId(carId);
    }


}
