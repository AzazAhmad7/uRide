package com.project.uRide.strategies;

import com.project.uRide.entities.Driver;
import com.project.uRide.entities.RideRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DriverMatchingStrategy {
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
