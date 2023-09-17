package com.rent.car.rental.service.impl.impl;

import com.mysql.cj.log.Log;
import com.rent.car.rental.converter.Convert;
import com.rent.car.rental.dto.CarDto;
import com.rent.car.rental.dto.LoginResponseDto;
import com.rent.car.rental.dto.PersonDto;
import com.rent.car.rental.dto.ResponseDto;
import com.rent.car.rental.entity.CarEntity;
import com.rent.car.rental.entity.PersonEntity;
import com.rent.car.rental.entity.ReviewEntity;
import com.rent.car.rental.entity.TokenEntity;
import com.rent.car.rental.exeption.ResourceNotFoundExeption;
import com.rent.car.rental.repository.PersonRepository;
import com.rent.car.rental.repository.TokenRepository;
import org.apache.catalina.authenticator.NonLoginAuthenticator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class PersonServiceImpl {


    private PersonRepository personRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private TokenRepository tokenRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;

    }

    public PersonDto createPerson(PersonDto personDto) throws NoSuchAlgorithmException {
        PersonEntity personEntity = modelMapper.map(personDto, PersonEntity.class);
        String hashPassword = convertPasswordToMD5(personDto.getPassword());
        personEntity.setPassword(hashPassword);
        PersonEntity personEntity1 = personRepository.save(personEntity);
        return modelMapper.map(personEntity1, PersonDto.class);
    }

    public void deletePerson(Long personId) {

        PersonEntity existingPerson = personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundExeption("PersonEntity", "id", personId));

        personRepository.delete(existingPerson);
    }

    private String convertPasswordToMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md5.digest(password.getBytes());

        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : hashBytes) {
            stringBuilder.append(b);
        }
        String result = stringBuilder.toString();
        return result;
    }

    public LoginResponseDto login(PersonDto personDto) throws Exception {
        PersonEntity entity = new PersonEntity();
        String password = personDto.getPassword();
        String md5Password = convertPasswordToMD5(password);
        try {
            entity = personRepository.findByUserNameAndPassword(personDto.getUsername(), md5Password);
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Username or password is wrong");
        }

        LoginResponseDto responseDto = modelMapper.map(entity, LoginResponseDto.class);
        String tokenString = this.findOrCreateToken(entity.getId());
        System.out.println(tokenString);
        responseDto.setToken(tokenString);

        return responseDto;
    }

    public String findOrCreateToken(Long personid) {
        TokenEntity token = null;

        try {
            token = tokenRepository.findByPersonEntity(new PersonEntity(personid));
        } catch (Exception e) {
            System.out.println(e);
        }

        if (token == null) {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setPersonEntity(new PersonEntity(personid));
            tokenEntity.setToken(generateToken());

            String tokenex = null;

            try {
                TokenEntity tokenex1 = tokenRepository.save(tokenEntity);
                tokenex = tokenex1.getToken();
            } catch (Exception e) {
                System.out.println(e);
            }
            return tokenex;
        } else {
            return token.getToken();
        }


    }

    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        String randomToken = Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);


        return randomToken;
    }
}