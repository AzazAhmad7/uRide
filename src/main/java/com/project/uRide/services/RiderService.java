package com.project.uRide.services;

import com.project.uRide.dtos.RideDTO;
import com.project.uRide.dtos.RideRequestDTO;
import com.project.uRide.dtos.RiderDTO;
import com.project.uRide.entities.Rider;
import com.project.uRide.entities.User;

import java.util.List;

public interface RiderService {
    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);
    RideDTO cancelRide(Long rideId);
    RideDTO rateDriver(Long rideId, Integer rating);
    RiderDTO getMyProfile();
    List<RideDTO> getAllRides();
    Rider createNewRider(User user);
    Rider getCurrentRider();
}
