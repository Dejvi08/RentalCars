package com.rent.car.rental.repository;

import com.rent.car.rental.entity.CarEntity;
import com.rent.car.rental.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity>findByCarEntity(CarEntity carEntity);
}
