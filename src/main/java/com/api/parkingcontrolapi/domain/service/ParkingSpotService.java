package com.api.parkingcontrolapi.domain.service;

import com.api.parkingcontrolapi.domain.port.ParkingSpotRepository;

public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }
}
