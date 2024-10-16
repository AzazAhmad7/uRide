package com.project.uRide.services.Impl;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RideDTO;
import com.project.uRide.dtos.RideRequestDTO;
import com.project.uRide.dtos.RiderDTO;
import com.project.uRide.entities.*;
import com.project.uRide.entities.enums.RideRequestStatus;
import com.project.uRide.entities.enums.RideStatus;
import com.project.uRide.exceptions.ResourceNotFoundException;
import com.project.uRide.repository.RideRequestRepository;
import com.project.uRide.repository.RiderRepository;
import com.project.uRide.services.DriverService;
import com.project.uRide.services.RatingService;
import com.project.uRide.services.RideService;
import com.project.uRide.services.RiderService;
import com.project.uRide.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRider(rider);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        Double fare = rideStrategyManager.getRideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = rideStrategyManager.getDriverMatchingStrategy(rider.getRating()).findMatchingDrivers(rideRequest);

        //TODO SEND THE NOTIFICATION TO ALL THE DRIVERS ABOUT THIS RIDE REQUEST

        return modelMapper.map(savedRideRequest, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Ride ride = rideService.getRideById(rideId);
        Rider currentRider = getCurrentRider();

        if(!ride.getRider().equals(currentRider)){
            throw new ResourceNotFoundException("Rider does not belong to this ride");
        }
        if(ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Ride Cannot be cancelled as it is already "+ride.getRideStatus());
        }
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        Driver driver = ride.getDriver();
        driverService.updateDriverAvailability(driver, true);

        return modelMapper.map(savedRide, RideDTO.class);
    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not ended, "+ride.getRideStatus());
        }
        Rider currentRider = getCurrentRider();
        if(!ride.getRider().equals(currentRider)){
            throw new ResourceNotFoundException("Rider does not belong to this ride");
        }
        return ratingService.rateDriver(ride, rating);
    }

    @Override
    public RiderDTO getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDTO.class);
    }

    @Override
    public Page<RideDTO> getAllRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider,pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found with id "+1));
    }
}
