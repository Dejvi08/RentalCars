package com.rent.car.rental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String userName;
    private String surname;
    private int age;
    private String email;
    @Column(name = "credit_card_number")
    private int creditCardNumber;
    private String password;
    @OneToOne(mappedBy = "personEntity", cascade = CascadeType.ALL)
    private TokenEntity token;

    public PersonEntity(Long personid) {
        this.id = personid;
    }
}
