package com.project.uRide.services.Impl;

import com.project.uRide.entities.Payment;
import com.project.uRide.entities.Ride;
import com.project.uRide.entities.enums.PaymentStatus;
import com.project.uRide.exceptions.ResourceNotFoundException;
import com.project.uRide.repository.PaymentRepository;
import com.project.uRide.services.PaymentService;
import com.project.uRide.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    public void processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride).orElseThrow(()-> new ResourceNotFoundException("Payment not found "+ride.getId()));
        paymentStrategyManager.getPaymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createPayment(Ride ride) {
        Payment payment = Payment.builder()
                .paymentMethod(ride.getPaymentMethod())
                .PaymentStatus(PaymentStatus.PENDING)
                .amount(ride.getFare())
                .ride(ride)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        return paymentRepository.save(payment);
    }
}
