package com.rent.car.rental.repository;

import com.rent.car.rental.entity.CarEntity;
import com.rent.car.rental.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query("SELECT r FROM ReviewEntity r WHERE r.carEntity.id = :carId")
    List<ReviewEntity> findByCarEntity(@Param("carId") Long carId);
}
