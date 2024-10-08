package com.project.uRide.dtos;

import com.project.uRide.entities.enums.PaymentMethod;
import com.project.uRide.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDTO {
    private long id;
    private DriverDTO driver;
    private RiderDTO rider;
    private PointDTO pickUpLocation;
    private PointDTO dropOffLocation;
    private LocalDateTime createdTime;
    private PaymentMethod paymentMethod;
    private RideStatus rideStatus;
    private String otp;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
