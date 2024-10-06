package com.project.uRide.strategies.impl;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RideRequestDTO;
import com.project.uRide.entities.RideRequest;
import com.project.uRide.repository.DriverRepository;
import com.project.uRide.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<DriverDTO> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearestDrivers(rideRequest.getPickUpLocation());
    }
}
