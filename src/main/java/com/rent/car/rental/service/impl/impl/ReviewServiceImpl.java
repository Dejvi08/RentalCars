package com.rent.car.rental.service.impl.impl;

import com.rent.car.rental.converter.Convert;
import com.rent.car.rental.dto.ReviewDto;
import com.rent.car.rental.entity.CarEntity;
import com.rent.car.rental.entity.ReviewEntity;
import com.rent.car.rental.exeption.BlogApiExeption;
import com.rent.car.rental.exeption.ResourceNotFoundExeption;
import com.rent.car.rental.repository.CarRepository;
import com.rent.car.rental.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    public ReviewDto createReview(long carId, ReviewDto reviewDto) {
        ReviewEntity review = Convert.mapToEntity(reviewDto);
        CarEntity carEntity = carRepository.findById(carId).orElseThrow(()->new ResourceNotFoundExeption("CarEntity", "id",
                carId));
        review.setCarEntity(carEntity);
        ReviewEntity newReview = reviewRepository.save(review);
        return Convert.mapToDto(newReview);
    }
    public List<ReviewDto> getReviewsByCarId(Long carId) {
        List<ReviewEntity> reviews = reviewRepository.findByCarEntity(carId);
        return reviews.stream().map(Convert::mapToDto).collect(Collectors.toList());
    }
    public ReviewDto getReviewById(long carId, long reviewId) {
        CarEntity car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundExeption("CarEntity", "id",
                carId));

        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundExeption("ReviewEntity", "id",
                reviewId));

        if (!review.getCarEntity().getId().equals(car.getId())) {
            throw new BlogApiExeption(HttpStatus.BAD_REQUEST, "Review doesen't not belong to post");

        }
        return Convert.mapToDto(review);
    }
    public ReviewDto updateReview(Long reviewId, ReviewDto updatedReviewDto) {

        ReviewEntity existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundExeption("ReviewEntity", "id", reviewId));

        existingReview.setDescription(updatedReviewDto.getDescription());
        existingReview.setRate(updatedReviewDto.getRate());


        ReviewEntity updatedReview = reviewRepository.save(existingReview);

        return Convert.mapToDto(updatedReview);
    }
    public void deleteReview(Long reviewId) {

        ReviewEntity existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundExeption("ReviewEntity", "id", reviewId));

        reviewRepository.delete(existingReview);
    }


}
