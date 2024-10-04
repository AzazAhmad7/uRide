package com.project.uRide.services;

import com.project.uRide.dtos.RideDTO;
import com.project.uRide.dtos.RideRequestDTO;
import com.project.uRide.dtos.RiderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface RiderService {
    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);
    RideDTO cancelRide(Long rideId);
    RideDTO rateDriver(Long rideId, Integer rating);
    RiderDTO getMyProfile();
    List<RideDTO> getAllRides();
}
