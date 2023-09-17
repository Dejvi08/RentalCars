package com.rent.car.rental.controller;

import com.rent.car.rental.dto.ReviewDto;
import com.rent.car.rental.entity.ReviewEntity;
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

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/cars/{carId}/review_entity")
    public ResponseEntity<ReviewDto> createReview(@PathVariable("carId") long carId,@RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(carId, reviewDto), HttpStatus.CREATED);

    }
    @GetMapping("/cars/{carId}/review_entity/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable("carId") Long carId, @PathVariable("reviewId") Long reviewId) {
        ReviewDto review = reviewService.getReviewById(carId, reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
    @GetMapping("/cars/{carId}/review_entity")
    public ResponseEntity<List<ReviewDto>> getAllReviewsByCarId(@PathVariable("carId") Long carId) {
        List<ReviewDto> reviews = reviewService.getReviewsByCarId(carId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable("reviewId") Long reviewId,
            @RequestBody ReviewDto updatedReviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(reviewId, updatedReviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }


    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "Review is successfully deleted";
    }
    }

