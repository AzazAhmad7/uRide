package com.project.uRide.services;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RideDTO;
import com.project.uRide.dtos.RiderDTO;
import com.project.uRide.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {
    RideDTO acceptRide(Long rideRequestId);
    RideDTO cancelRide(Long rideId);
    RideDTO startRide(Long rideId, String otp);
    RideDTO endRide(Long rideId);
    RiderDTO rateRider(Long rideId, Integer rating);
    DriverDTO getMyProfile();
    Page<RideDTO> getAllRides(PageRequest pageRequest);
    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean available);
    Driver createNewDriver(Driver driver);
}
