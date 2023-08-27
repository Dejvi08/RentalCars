package com.rent.car.rental.service.impl.impl;

import com.rent.car.rental.converter.Convert;
import com.rent.car.rental.dto.ReviewDto;
import com.rent.car.rental.entity.CarEntity;
import com.rent.car.rental.entity.ReviewEntity;
import com.rent.car.rental.exeption.ResourceNotFoundExeption;
import com.rent.car.rental.repository.CarRepository;
import com.rent.car.rental.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl {


    private ReviewRepository reviewRepository;
    private CarRepository carRepository;
@Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CarRepository carRepository) {
        this.reviewRepository = reviewRepository;
        this.carRepository = carRepository;
    }


    public ReviewDto createComment(long carId, ReviewDto reviewDto) {
        ReviewEntity review = Convert.mapToEntity(reviewDto);
        CarEntity carEntity = carRepository.findById(carId).orElseThrow(()->new ResourceNotFoundExeption("CarEntity", "id",
                carId));
        review.setCarEntity(carEntity);
        ReviewEntity newReview = reviewRepository.save(review);
        return Convert.mapToDto(newReview);
    }
    public List<ReviewDto> getReviewsByCarId(Long carId) {
        List<ReviewEntity> reviews = reviewRepository.findByCarEntity(carId);
        return reviews.stream().map(review -> Convert.mapToDto(review)).collect(Collectors.toList());
    }



}
