package com.project.uRide.strategies;

import com.project.uRide.dtos.RideRequestDTO;

public interface RideFareCalculationStrategy {
    double calculateFare(RideRequestDTO rideRequestDTO);
}
