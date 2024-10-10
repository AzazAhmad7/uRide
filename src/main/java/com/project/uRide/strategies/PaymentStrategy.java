package com.project.uRide.strategies;

import com.project.uRide.entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_COMMISION = 0.3;

    void processPayment(Payment payment);

}
