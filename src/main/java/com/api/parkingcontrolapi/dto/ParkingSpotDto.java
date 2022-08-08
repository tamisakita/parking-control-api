package com.api.parkingcontrolapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ParkingSpotDto {

    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    @Size(max = 7)
    private String LicensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String colorCar;
    @NotBlank
    private LocalDateTime registrationDate;
    @NotBlank
    private  String responsibleName;
    @NotBlank
    private String apartment;
    @NotBlank
    private String block;
}
