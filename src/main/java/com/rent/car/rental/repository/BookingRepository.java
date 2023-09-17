package com.rent.car.rental.repository;

import com.rent.car.rental.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByPersonId(Long personId);
    List<BookingEntity> findByCarId(Long carId);
}
