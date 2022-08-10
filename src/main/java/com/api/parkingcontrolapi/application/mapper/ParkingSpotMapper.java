package com.api.parkingcontrolapi.application.mapper;

import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import java.util.function.Supplier;

@UtilityClass
public class ParkingSpotMapper {

    private Supplier<ModelMapper> modelMapperSupplier = ModelMapper::new;
}
