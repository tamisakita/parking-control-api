package com.api.parkingcontrolapi.application.repository.jpa;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PARKING_SPOT")
public class ParkingSpotModel {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PARKING_SPOT_NUMBER", nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;

    @Column(name = "LICENSE_PLATE_CAR",nullable = false, unique = true, length = 7)
    private String licensePlateCar;

    @Column(name = "BRAND_CAR", nullable = false, length = 70)
    private String brandCar;

    @Column(name = "MODEL_CAR", nullable = false, length = 70)
    private String modelCar;

    @Column(name = "COLOR_CAR", nullable = false, length = 70)
    private String colorCar;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "RESPONSIBLE_NAME", nullable = false, length = 130)
    private  String responsibleName;

    @Column(name = "APARTMENT", nullable = false, length = 30)
    private String apartment;

    @Column(name = "BLOCK", nullable = false, length = 30)
    private String block;

    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;

    @PrePersist
    public void prePersist() {
        registrationDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updateDateTime = LocalDateTime.now();
    }
}
