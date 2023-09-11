package com.rent.car.rental.repository;

import com.rent.car.rental.entity.PersonEntity;
import com.rent.car.rental.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findByPersonEntity(PersonEntity person);

}
