package com.api.parkingcontrolapi.application.mapper;

import com.api.parkingcontrolapi.application.presentation.representation.ParkingSpotRequestDto;
import com.api.parkingcontrolapi.application.presentation.representation.ParkingSpotResponseDto;
import com.api.parkingcontrolapi.application.repository.jpa.ParkingSpotModel;
import com.api.parkingcontrolapi.domain.domain.ParkingSpot;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@UtilityClass
public class ParkingSpotMapper {

    private Supplier<ModelMapper> modelMapperSupplier = ModelMapper::new;

    public ParkingSpot representationToDomain(ParkingSpotRequestDto representation) {
        return modelMapperSupplier.get().map(representation, ParkingSpot.class);
    }

    public ParkingSpotResponseDto domainToRepresentation(ParkingSpot domain) {
        return modelMapperSupplier.get().map(domain, ParkingSpotResponseDto.class);
    }

    public ParkingSpotModel domainToEntity(ParkingSpot domain) {
        return modelMapperSupplier.get().map(domain, ParkingSpotModel.class);
    }

    public ParkingSpot entityToDomain(ParkingSpotModel entity) {
        return modelMapperSupplier.get().map(entity, ParkingSpot.class);
    }

    public List<ParkingSpotResponseDto> domainListToRepresentationList(List<ParkingSpot> domainList) {
        List<ParkingSpotResponseDto> parkingSpotResponseDtoList = new ArrayList<>();
        for (ParkingSpot parkingSpot : domainList) {
            parkingSpotResponseDtoList.add(domainToRepresentation(parkingSpot));
        }

        return parkingSpotResponseDtoList;
    }

    public List<ParkingSpot> entityListToDomainList(List<ParkingSpotModel> entityList) {
        List<ParkingSpot> domainList = new ArrayList<>();
        for (ParkingSpotModel parkingSpotModel : entityList) {
            domainList.add(entityToDomain(parkingSpotModel));
        }

        return domainList;
    }
}
