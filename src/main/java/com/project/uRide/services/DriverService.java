package com.project.uRide.services;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RideDTO;
import com.project.uRide.dtos.RiderDTO;

import java.util.List;

public interface DriverService {
    RideDTO acceptRide(Long rideId);
    RideDTO cancelRide(Long rideId);
    RideDTO startRide(Long rideId);
    RideDTO endRide(Long rideId);
    RiderDTO rateRider(Long rideId, Integer rating);
    DriverDTO getMyProfile();
    List<RideDTO> getAllRides();
}
