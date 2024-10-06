package com.project.uRide.dtos;

import com.project.uRide.entities.Rider;
import com.project.uRide.entities.enums.PaymentMethod;
import com.project.uRide.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDTO {
    private long id;
    private RiderDTO rider;
    private PointDTO pickUpLocation;
    private PointDTO dropOffLocation;
    private LocalDateTime requestedTime;
    private Double fare;
    private PaymentMethod paymentMethod;
    private RideRequestStatus rideRequestStatus;
}
