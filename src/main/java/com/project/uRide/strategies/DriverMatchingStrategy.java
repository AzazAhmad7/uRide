package com.project.uRide.strategies;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RideRequestDTO;
import com.project.uRide.entities.RideRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DriverMatchingStrategy {
    List<DriverDTO> findMatchingDrivers(RideRequest rideRequest);
}
