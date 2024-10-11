package com.project.uRide.controllers;

import com.project.uRide.dtos.*;
import com.project.uRide.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rider")
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDTO> requestRide(@RequestBody RideRequestDTO rideRequestDTO) {
        return ResponseEntity.ok(riderService.requestRide(rideRequestDTO));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver")
    public ResponseEntity<DriverDTO> rateRider(@RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(riderService.rateDriver(ratingDTO.getRideId(), ratingDTO.getRating()));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<RiderDTO> getRiderProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping("/getAllRides")
    public ResponseEntity<Page<RideDTO>> getAllMyRides(@RequestParam(defaultValue = "0", required = false) Integer pageOffSet,
                                                       @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageOffSet, pageSize, Sort.by(Sort.Direction.DESC, "createdTime"));
        return ResponseEntity.ok(riderService.getAllRides(pageRequest));
    }

}
