package com.project.uRide.strategies.impl;

import com.project.uRide.dtos.RideRequestDTO;
import com.project.uRide.entities.RideRequest;
import com.project.uRide.strategies.RideFareCalculationStrategy;

public class SurgePricingRideFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0;
    }
}
