package com.project.uRide.controllers;

import com.project.uRide.dtos.*;
import com.project.uRide.entities.Ride;
import com.project.uRide.services.DriverService;
import com.project.uRide.services.RideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<RideDTO> endRide(@PathVariable Long rideId) {
        RideDTO rideDTO = driverService.endRide(rideId);
        return ResponseEntity.ok(rideDTO);
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/rateRider/{rideId}/{rating}")
    public ResponseEntity<RiderDTO> rateRider(@PathVariable Long rideId, @PathVariable Integer rating){
        return ResponseEntity.ok(driverService.rateRider(rideId, rating));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<DriverDTO> getMyProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping("/getAllRides")
    public ResponseEntity<Page<RideDTO>> getAllMyRide(@RequestParam(defaultValue = "0", required = false) Integer pageOffSet,
                                                     @RequestParam(defaultValue = "10", required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffSet, pageSize, Sort.by(Sort.Direction.DESC, "createdTime"));
        return ResponseEntity.ok(driverService.getAllRides(pageRequest));
    }

}
