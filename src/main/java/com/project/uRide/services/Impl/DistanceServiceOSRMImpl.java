package com.project.uRide.services.Impl;

import com.project.uRide.services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point pickupLocation, Point destinationLocation) {

        //TODO call the third party API OSRM to calculate distance
        return 0;
    }
}
