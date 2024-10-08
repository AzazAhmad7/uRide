package com.project.uRide.controllers;

import com.project.uRide.dtos.RideDTO;
import com.project.uRide.dtos.RideStartDTO;
import com.project.uRide.entities.Ride;
import com.project.uRide.services.DriverService;
import com.project.uRide.services.RideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Slf4j
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequestId) {
        RideDTO rideDTO = driverService.acceptRide(rideRequestId);
        return ResponseEntity.ok(rideDTO);
    }

    @PostMapping("/startRide/{rideId}")
    public ResponseEntity<RideDTO> startRide(@PathVariable Long rideId, @RequestBody RideStartDTO rideStartDTO) {
        RideDTO rideDTO = driverService.startRide(rideId, rideStartDTO.getOtp());
        return ResponseEntity.ok(rideDTO);
    }


}
