package com.project.uRide.repository;

import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Rating;
import com.project.uRide.entities.Ride;
import com.project.uRide.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);
    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
