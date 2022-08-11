package com.api.parkingcontrolapi.domain.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpot {

    private Long id;
    private String parkingSpotNumber;
    private String licensePlateCar;
    private String brandCar;
    private String modelCar;
    private String colorCar;
    private String responsibleName;
    private String apartment;
    private String block;
    private LocalDateTime registrationDate;
}
