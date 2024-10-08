package com.project.uRide.strategies.impl;

import com.project.uRide.entities.Driver;
import com.project.uRide.entities.RideRequest;
import com.project.uRide.repository.DriverRepository;
import com.project.uRide.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HighestRatedDriverMatchingStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickUpLocation());
    }
}
