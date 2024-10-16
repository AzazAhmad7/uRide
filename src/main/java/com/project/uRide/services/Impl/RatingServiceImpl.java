package com.project.uRide.services.Impl;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.RiderDTO;
import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Rating;
import com.project.uRide.entities.Ride;
import com.project.uRide.entities.Rider;
import com.project.uRide.exceptions.ResourceNotFoundException;
import com.project.uRide.exceptions.RuntimeConflictException;
import com.project.uRide.repository.DriverRepository;
import com.project.uRide.repository.RatingRepository;
import com.project.uRide.repository.RiderRepository;
import com.project.uRide.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDTO rateDriver(Ride ride, Integer rating) {
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating is not found"));
        if(ratingObj.getDriverRating() != null){
            throw new RuntimeConflictException("You can't rate again");
        }
        ratingObj.setDriverRating(rating);

        ratingRepository.save(ratingObj);

        double newRating = ratingRepository.findByDriver(ride.getDriver()).stream()
                .mapToDouble(rating1 -> rating1.getDriverRating())
                .average().orElse(0.0);

        ride.getDriver().setRating(newRating);
        Driver driver = driverRepository.save(ride.getDriver());

        return modelMapper.map(driver, DriverDTO.class);
    }

    @Override
    public RiderDTO rateRider(Ride ride, Integer rating) {
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating is not found"));
        if(ratingObj.getRiderRating() != null){
            throw new RuntimeConflictException("You can't rate again");
        }
        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository.findByRider(ride.getRider())
                .stream()
                .mapToDouble(rating1 -> rating1.getRiderRating())
                .average().orElse(0.0);
        ride.getDriver().setRating(newRating);
        Rider rider = riderRepository.save(ride.getRider());
        return modelMapper.map(rider, RiderDTO.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .driver(ride.getDriver())
                .rider(ride.getRider())
                .ride(ride)
                .build();
    }
}
