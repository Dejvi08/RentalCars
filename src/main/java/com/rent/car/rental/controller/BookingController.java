package com.rent.car.rental.controller;

import com.rent.car.rental.dto.BookingDto;
import com.rent.car.rental.service.impl.impl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired


    private BookingService bookingService;
    @PostMapping("/persons/{personId}/cars/{carId}")
    public ResponseEntity<BookingDto> createBooking(@PathVariable("personId") Long personId, @RequestBody BookingDto bookingDto,
                                                    @PathVariable("carId") Long carId) {
        return new ResponseEntity<>(bookingService.createBooking(bookingDto, personId, carId), HttpStatus.CREATED);
    }
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDto> getBookingById(
            @PathVariable("bookingId") Long bookingId
    ) {
        BookingDto bookingDto = bookingService.getBookingById(bookingId);

        if (bookingDto != null) {
            return ResponseEntity.ok(bookingDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
        @GetMapping("/persons/{personId}")
        public List<BookingDto>getAllBookingsByPersonId(@PathVariable("personId") Long personId){
        return bookingService.getAllBookingsByPersonId(personId);
        }
    @GetMapping("/cars/{carId}")
    public List<BookingDto>getAllBookingsByCarId(@PathVariable("carId") Long carId){
        return bookingService.getAllBookingsByPersonId(carId);
    }
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDto> updateBooking(
            @PathVariable("bookingId") Long bookingId,
            @RequestBody BookingDto updatedBookingDto
    ) {
        BookingDto updatedBooking = bookingService.updateBooking(bookingId, updatedBookingDto);

        if (updatedBooking != null) {
            return ResponseEntity.ok(updatedBooking);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{personId}")
    public String deleteBookingByPersonId(@PathVariable("personId") Long Personid) {
        bookingService.deleteBookingByPersonId(Personid);
        return "BOOKING IS SUCCESSFULLY DELETED";
    }
    }
