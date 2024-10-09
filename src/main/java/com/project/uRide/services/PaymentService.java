package com.project.uRide.services;

import com.project.uRide.entities.Payment;
import com.project.uRide.entities.Ride;

public interface PaymentService {

    void processPayment(Payment payment);
    Payment createPayment(Ride ride);

}
