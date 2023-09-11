package com.rent.car.rental.repository;

import com.rent.car.rental.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    PersonEntity findByUserNameAndPassword(String userName, String password);
}
