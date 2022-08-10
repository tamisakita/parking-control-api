package com.api.parkingcontrolapi.application.presentation.representation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingSpotResponseDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("parkingSpotNumber")
    private String parkingSpotNumber;

    @Size(max = 7)
    @JsonProperty("licensePlateCar")
    private String licensePlateCar;

    @JsonProperty("brandCar")
    private String brandCar;

    @JsonProperty("modelCar")
    private String modelCar;

    @JsonProperty("colorCar")
    private String colorCar;

    @JsonProperty("responsibleName")
    private  String responsibleName;

    @JsonProperty("apartment")
    private String apartment;

    @JsonProperty("block")
    private String block;
}
