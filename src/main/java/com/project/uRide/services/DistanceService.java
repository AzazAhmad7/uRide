package com.project.uRide.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
   double calculateDistance(Point pickupLocation, Point destinationLocation);
}
