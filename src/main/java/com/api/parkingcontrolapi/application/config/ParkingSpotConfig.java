package com.api.parkingcontrolapi.application.config;

import com.api.parkingcontrolapi.domain.port.ParkingSpotRepository;
import com.api.parkingcontrolapi.domain.service.ParkingSpotService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParkingSpotConfig {

    @Bean
    public ParkingSpotService parkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        return new ParkingSpotService(parkingSpotRepository);
    }
}
