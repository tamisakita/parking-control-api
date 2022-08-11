package com.api.parkingcontrolapi.application.presentation.controller;

import com.api.parkingcontrolapi.application.ParkingSpotRepositoryImpl;
import com.api.parkingcontrolapi.application.mapper.ParkingSpotMapper;
import com.api.parkingcontrolapi.application.presentation.representation.ParkingSpotRequestDto;
import com.api.parkingcontrolapi.application.presentation.representation.ParkingSpotResponseDto;
import com.api.parkingcontrolapi.domain.domain.ParkingSpot;
import com.api.parkingcontrolapi.domain.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    private final ParkingSpotService parkingSpotService;

    final ParkingSpotRepositoryImpl parkingSpotRepositoryImpl;

    public ParkingSpotController(ParkingSpotService parkingSpotService, ParkingSpotRepositoryImpl parkingSpotRepositoryImpl) {
        this.parkingSpotService = parkingSpotService;
        this.parkingSpotRepositoryImpl = parkingSpotRepositoryImpl;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotRequestDto parkingSpotDto) {
        if (parkingSpotRepositoryImpl.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License Plate Car is already in use!");
        }
        if (parkingSpotRepositoryImpl.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot is already in use!");
        }
        if (parkingSpotRepositoryImpl.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Spot already registered from this apartment/block!");
        }
        var parkingSpot = parkingSpotService.saveParkingSpot(ParkingSpotMapper.representationToDomain(parkingSpotDto));
        parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        if (nonNull(parkingSpot)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ParkingSpotMapper.domainToRepresentation(parkingSpot));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<ParkingSpotResponseDto>> getAllParkingSpots() {
        var domainList = parkingSpotService.getAllParkingSpot();
        var representationList = ParkingSpotMapper.domainListToRepresentationList(domainList);
        return ResponseEntity.status(HttpStatus.OK).body(representationList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParkingSpotsById(@PathVariable(value = "id") Long id) {
        ParkingSpot parkingSpotById = parkingSpotService.getParkingSpotById(id);
        if (nonNull(parkingSpotById)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ParkingSpotMapper.domainToRepresentation(parkingSpotById));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParkingSpot(@PathVariable(value = "id") Long id) {
        parkingSpotService.deleteParkingSpot(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") Long id,
                                                    @RequestBody ParkingSpotRequestDto parkingSpotDto){
        var parkingSpotUpdated = parkingSpotService.updateParkingSpot(id, ParkingSpotMapper.representationToDomain(parkingSpotDto));
        parkingSpotUpdated.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        var representation = ParkingSpotMapper.domainToRepresentation(parkingSpotUpdated);
        if (nonNull(representation)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(representation);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not found");
    }

}
