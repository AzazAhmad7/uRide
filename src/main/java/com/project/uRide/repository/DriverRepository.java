package com.project.uRide.repository;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickUpLocation) AS distance " +
            "FROM driver AS d " +
            "where available = true AND ST_Within(d.current_location, :pickUpLocation, 10000) ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)
    List<DriverDTO> findTenNearestDrivers(Point pickUpLocation);
}
