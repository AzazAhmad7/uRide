package com.project.uRide.strategies;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RideRequestDTO;

import java.util.List;

public interface DriverMatchingStrategy {
    List<DriverDTO> findMatchingDrivers(RideRequestDTO rideRequestDTO);
}
