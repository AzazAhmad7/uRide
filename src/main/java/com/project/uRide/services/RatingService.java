package com.project.uRide.services;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RiderDTO;
import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Ride;
import com.project.uRide.entities.Rider;

public interface RatingService {
    DriverDTO rateDriver(Ride ride, Integer rating);
    RiderDTO rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);
}
