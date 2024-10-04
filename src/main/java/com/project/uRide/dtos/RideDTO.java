package com.project.uRide.dtos;

import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Rider;
import com.project.uRide.entities.enums.PaymentMethod;
import com.project.uRide.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDTO {
    private long id;
    private DriverDTO driver;
    private RiderDTO rider;
    private Point pickUpLocation;
    private Point dropOffLocation;
    private LocalDateTime createdTime;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private String otp;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
