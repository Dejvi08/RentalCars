package com.rent.car.rental.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "review_entity")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Min(value = 1, message = "Ju lutem rate minimal esht 1")
    @Max(value = 5, message = "Ju lutem rate maksimal esht 5")

    private int rate;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity carEntity;

}
