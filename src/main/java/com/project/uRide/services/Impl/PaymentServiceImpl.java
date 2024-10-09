package com.project.uRide.services.Impl;

import com.project.uRide.entities.Payment;
import com.project.uRide.entities.Ride;
import com.project.uRide.repository.PaymentRepository;
import com.project.uRide.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {

    }

    @Override
    public Payment createPayment(Ride ride) {
        return null;
    }
}
