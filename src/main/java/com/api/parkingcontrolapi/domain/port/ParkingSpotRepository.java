package com.api.parkingcontrolapi.domain.port;

import com.api.parkingcontrolapi.domain.domain.ParkingSpot;
import java.util.List;

public interface ParkingSpotRepository {
    ParkingSpot saveParkingSpot(ParkingSpot parkingSpot);

    List<ParkingSpot> getAllParkingSpot();

    ParkingSpot getParkingSpotById(Long id);

    void deleteParkingSpot(Long id);

    ParkingSpot updateParkingSpot(Long id, ParkingSpot parkingSpot);

}
