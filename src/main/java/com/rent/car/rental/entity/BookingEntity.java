package com.rent.car.rental.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date bookingDate; // Date of the booking

    private String carModel; // Model of the booked car
    private int quantity;    // Quantity of cars booked, if applicable

    private String firstName; // First name of the booking person
    private String lastName;  // Last name of the booking person

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity car;


}
