package com.project.uRide.services;

import com.project.uRide.entities.RideRequest;

public interface RideRequestService {
    RideRequest getRideRequestById(Long id);

    void update(RideRequest rideRequest);
}
