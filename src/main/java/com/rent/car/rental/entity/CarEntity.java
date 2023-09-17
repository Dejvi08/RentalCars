package com.rent.car.rental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Ju lutem emri i modelit esht required")
    private String model;

    @NotEmpty(message = "Ju lutem ngjyra esht required")
    private String color;

    private int year;

    private int price;

    @Column(name = "fuel_type")
    private String fuelType;

    private String transmission;

    @OneToMany(mappedBy = "carEntity")
    private Set<ReviewEntity> reviews = new HashSet<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private Set<BookingEntity> bookings = new HashSet<>();


    public CarEntity(Long id) {
        this.id = id;
    }
}

