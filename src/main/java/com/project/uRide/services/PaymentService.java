package com.project.uRide.services;

import com.project.uRide.entities.Payment;
import com.project.uRide.entities.Ride;
import com.project.uRide.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);
    Payment createPayment(Ride ride);
    Payment updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);

}
