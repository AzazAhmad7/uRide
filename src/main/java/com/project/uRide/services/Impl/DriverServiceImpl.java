package com.project.uRide.services.Impl;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RideDTO;
import com.project.uRide.dtos.RiderDTO;
import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Ride;
import com.project.uRide.entities.RideRequest;
import com.project.uRide.entities.Rider;
import com.project.uRide.entities.enums.PaymentStatus;
import com.project.uRide.entities.enums.RideRequestStatus;
import com.project.uRide.entities.enums.RideStatus;
import com.project.uRide.exceptions.ResourceNotFoundException;
import com.project.uRide.repository.DriverRepository;
import com.project.uRide.repository.RatingRepository;
import com.project.uRide.services.*;
import com.project.uRide.strategies.PaymentStrategyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final PaymentStrategyManager paymentStrategyManager;
    private final RatingService ratingService;
    private final RatingRepository ratingRepository;

    @Override
    public RideDTO acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.getRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride request status is not PENDING, status is "+rideRequest.getRideRequestStatus());
        }
        Driver currentDriver = getCurrentDriver();

        if(!currentDriver.isAvailable()){
            throw new RuntimeException("Driver is not available "+currentDriver.getId());
        }
        updateDriverAvailability(currentDriver, false);
        Ride ride = rideService.createNewRide(rideRequest, currentDriver);
        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        if(ride == null){
            throw new ResourceNotFoundException("Ride not found with id "+rideId);
        }
        if(ride.getRideStatus().equals(RideStatus.ONGOING) || ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride cannot be cancelled if it is "+ride.getRideStatus());
        }
        rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        Driver currentDriver = getCurrentDriver();
        updateDriverAvailability(currentDriver, true);

        return modelMapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO startRide(Long rideId, String otp) {
        Ride ride = rideService.getRideById(rideId);
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not CONFIRMED, status is "+ride.getRideStatus());
        }
        if(!otp.equals(ride.getOtp())){
            throw new RuntimeException("OTP does not match");
        }
        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        paymentService.createPayment(savedRide);
        ratingService.createNewRating(savedRide);

        return modelMapper.map(savedRide, RideDTO.class);
    }

    @Override
    @Transactional
    public RideDTO endRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver currentDriver = getCurrentDriver();
        if(ride == null){
            throw new ResourceNotFoundException("Ride not found with id "+rideId);
        }
        if(!currentDriver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not the same as current driver");
        }
        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("Cannot End Ride, status is "+ride.getRideStatus());
        }

        paymentService.processPayment(ride);
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(currentDriver, true);

        return modelMapper.map(savedRide, RideDTO.class);
    }

    @Override
    public RiderDTO rateRider(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not ENDED, status is "+ride.getRideStatus());
        }
        Driver currentDriver = getCurrentDriver();
        if(!currentDriver.equals(ride.getDriver())){
            throw new RuntimeException("Driver does not own this ride");
        }
        return ratingService.rateRider(ride, rating);
    }

    @Override
    public DriverDTO getMyProfile() {
        Driver currentDriver = getCurrentDriver();
        return modelMapper.map(currentDriver, DriverDTO.class);
    }

    @Override
    public Page<RideDTO> getAllRides(PageRequest pageRequest) {
        Driver currentDriver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(currentDriver,pageRequest).map(
                ride -> modelMapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Driver getCurrentDriver() {

        //TODO USE SPRING SECURITY TO GET CURRENT DRIVER
        return driverRepository.findById(2L).orElseThrow(()-> new ResourceNotFoundException("Driver not found with id "+2));
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        return driverRepository.save(driver);
    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
