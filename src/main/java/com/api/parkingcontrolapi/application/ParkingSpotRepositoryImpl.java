package com.api.parkingcontrolapi.application;

import com.api.parkingcontrolapi.application.mapper.ParkingSpotMapper;
import com.api.parkingcontrolapi.application.repository.ParkingSpotJpa;
import com.api.parkingcontrolapi.domain.domain.ParkingSpot;
import com.api.parkingcontrolapi.domain.port.ParkingSpotRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {

    private final ParkingSpotJpa jpa;

    @Override
    public ParkingSpot saveParkingSpot(ParkingSpot parkingSpot) {
        var entity = jpa.save(ParkingSpotMapper.domainToEntity(parkingSpot));
        return ParkingSpotMapper.entityToDomain(entity);
    }

    @Override
    public List<ParkingSpot> getAllParkingSpot() {
        var entityList = jpa.findAll();
        return ParkingSpotMapper.entityListToDomainList(entityList);
    }

    @Override
    public ParkingSpot getParkingSpotById(Long id) {
        var parkingSpotId = jpa.findById(id);
        return parkingSpotId.map(ParkingSpotMapper::entityToDomain).orElse(null);
    }

    @Override
    public void deleteParkingSpot(Long id) {
        var parkingSpotById = jpa.findById(id);

        if (parkingSpotById.isPresent()) {
            jpa.deleteById(id);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Parking Spot doesn't exist");
        }
    }

    @Override
    public ParkingSpot updateParkingSpot(Long id, ParkingSpot parkingSpot) {
        var parkingSpotId = jpa.findById(id);

        if (parkingSpotId.isPresent()) {
            parkingSpot.setId(parkingSpotId.get().getId());
            parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
            var entity = jpa.save(ParkingSpotMapper.domainToEntity(parkingSpot));
            return ParkingSpotMapper.entityToDomain(entity);
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Parking Spot doesn't exist");
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return jpa.existsByLicensePlateCar(licensePlateCar);
    }


    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return jpa.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return jpa.existsByApartmentAndBlock(apartment, block);
    }

}
