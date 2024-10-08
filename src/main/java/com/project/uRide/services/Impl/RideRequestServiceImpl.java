package com.project.uRide.services.Impl;

import com.project.uRide.entities.RideRequest;
import com.project.uRide.exceptions.ResourceNotFoundException;
import com.project.uRide.repository.RideRequestRepository;
import com.project.uRide.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest getRideRequestById(Long id) {
        return rideRequestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Ride Request not found with id "+id));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId()).orElseThrow(() -> new ResourceNotFoundException("ride Request no found with id "+rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }
}
