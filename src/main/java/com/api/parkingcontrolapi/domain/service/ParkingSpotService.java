package com.api.parkingcontrolapi.domain.service;

import com.api.parkingcontrolapi.domain.domain.ParkingSpot;
import com.api.parkingcontrolapi.domain.port.ParkingSpotRepository;

import java.util.List;

public class ParkingSpotService {
    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ParkingSpot saveParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotRepository.saveParkingSpot(parkingSpot);
    }

    public List<ParkingSpot> getAllParkingSpot() {
        return parkingSpotRepository.getAllParkingSpot();
    }

    public ParkingSpot getParkingSpotById(Long id) {
        return parkingSpotRepository.getParkingSpotById(id);
    }

    public void deleteParkingSpot(Long id) {
        parkingSpotRepository.deleteParkingSpot(id);
    }

    public ParkingSpot updateParkingSpot(Long id, ParkingSpot parkingSpot) {
        return parkingSpotRepository.updateParkingSpot(id, parkingSpot);
    }

}
