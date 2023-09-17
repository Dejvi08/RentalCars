package com.rent.car.rental.service.impl.impl;

import com.rent.car.rental.converter.Convert;
import com.rent.car.rental.dto.BookingDto;
import com.rent.car.rental.dto.CarDto;
import com.rent.car.rental.dto.PersonDto;
import com.rent.car.rental.entity.BookingEntity;
import com.rent.car.rental.entity.CarEntity;
import com.rent.car.rental.entity.PersonEntity;
import com.rent.car.rental.exeption.ResourceNotFoundExeption;
import com.rent.car.rental.repository.BookingRepository;
import com.rent.car.rental.repository.CarRepository;
import com.rent.car.rental.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private PersonRepository personRepository;
    private CarRepository carRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public BookingService(BookingRepository bookingRepository, PersonRepository personRepository, CarRepository carRepository) {
        this.bookingRepository = bookingRepository;
        this.personRepository = personRepository;
        this.carRepository = carRepository;

    }


    public BookingDto createBooking(BookingDto bookingDto, Long personId, Long carId) {

        PersonEntity person = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundExeption("PersonEntity", "id", personId));
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundExeption("CarEntity", "id", carId));

        BookingEntity bookingEntity = modelMapper.map(bookingDto, BookingEntity.class);


        bookingEntity.setBookingDate(new Date());
        bookingEntity.setCarModel(car.getModel());


        bookingEntity.setPerson(person);
        bookingEntity.setCar(car);


        BookingEntity savedBookingEntity = bookingRepository.save(bookingEntity);


        return modelMapper.map(savedBookingEntity, BookingDto.class);
    }

    public BookingDto getBookingById(Long bookingId) {

        BookingEntity booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundExeption("BookingEntity", "id", bookingId));

        BookingDto bookingDto = Convert.mapToDto(booking);
        return bookingDto;
    }


    public List<BookingDto> getAllBookingsByPersonId(Long personId) {
        List<BookingEntity> bookings = bookingRepository.findByPersonId(personId);


        return bookings.stream().map(booking -> Convert.mapToDto(booking)).collect(Collectors.toList());
    }
    public List<BookingDto> getAllBookingsByCarId(Long carId) {
        List<BookingEntity> bookings = bookingRepository.findByPersonId(carId);


        return bookings.stream().map(booking -> Convert.mapToDto(booking)).collect(Collectors.toList());
    }
    public BookingDto updateBooking(Long bookingId, BookingDto updatedBooking) {

        BookingEntity existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundExeption("BookingEntity", "id", bookingId));


        existingBooking.setBookingDate(updatedBooking.getBookingDate());
        existingBooking.setCarModel(updatedBooking.getCarModel());
        existingBooking.setQuantity(updatedBooking.getQuantity());


        BookingEntity updatedEntity = bookingRepository.save(existingBooking);


        return Convert.mapToDto(updatedEntity);
    }
    public void deleteBookingByPersonId(Long Personid) {
        bookingRepository.deleteById(Personid);
    }





}
