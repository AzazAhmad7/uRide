package com.project.uRide.services;

import com.project.uRide.dtos.RideRequestDTO;
import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Ride;
import com.project.uRide.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {
    Ride getRideById(Long rideId);
    void matchWithDrivers(RideRequestDTO rideRequestDTO);
    Ride createNewRide(RideRequestDTO rideRequestDTO, Driver driver);
    Ride updateRideStatus(Long rideId, RideStatus rideStatus);
    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);
    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
