package com.project.uRide.services.Impl;

import com.project.uRide.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point pickupLocation, Point destinationLocation) {

        String uri = pickupLocation.getX()+","+pickupLocation.getY()+";"+destinationLocation.getX()+","+destinationLocation.getY();

        try{
            OSRMResponseDTO osrmResponseDTO = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSRMResponseDTO.class);

            return osrmResponseDTO.getRoutes().get(0).getDistance()/1000;
        }catch(Exception e){
            throw new RuntimeException("Error getting data from OSRM " +e.getMessage());
        }
    }
}

@Data
class OSRMResponseDTO{
    List<OSRMRoute> routes;
}

@Data
class OSRMRoute{
    private double distance;
}
