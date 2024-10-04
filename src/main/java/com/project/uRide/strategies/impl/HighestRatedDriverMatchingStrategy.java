package com.project.uRide.strategies.impl;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RideRequestDTO;
import com.project.uRide.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HighestRatedDriverMatchingStrategy implements DriverMatchingStrategy {
    @Override
    public List<DriverDTO> findMatchingDrivers(RideRequestDTO rideRequestDTO) {
        return List.of();
    }
}
