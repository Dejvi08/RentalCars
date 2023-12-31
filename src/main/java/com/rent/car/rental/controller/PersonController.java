package com.rent.car.rental.controller;

import com.rent.car.rental.dto.LoginResponseDto;
import com.rent.car.rental.dto.PersonDto;
import com.rent.car.rental.service.impl.impl.PersonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) throws NoSuchAlgorithmException {
        return new ResponseEntity<>(personService.createPerson(personDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{personId}")
    public ResponseEntity<String> deletePerson(@PathVariable("personId") Long personId) {
        personService.deletePerson(personId);
        return new ResponseEntity<>("Person with ID " + personId + " deleted successfully", HttpStatus.OK);
    }
    @PostMapping
    @RequestMapping("/login")
    public LoginResponseDto login(@RequestBody PersonDto personDto) throws Exception {
        return personService.login(personDto);
    }
}
