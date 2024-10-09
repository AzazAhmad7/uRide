package com.project.uRide.strategies.impl;

import com.project.uRide.entities.Payment;
import com.project.uRide.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class WalletPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(Payment payment) {

    }
}
