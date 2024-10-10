package com.project.uRide.strategies.impl;

import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Payment;
import com.project.uRide.entities.enums.PaymentStatus;
import com.project.uRide.entities.enums.TransactionMethod;
import com.project.uRide.repository.PaymentRepository;
import com.project.uRide.services.PaymentService;
import com.project.uRide.services.WalletService;
import com.project.uRide.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();

        double platformCommision = payment.getAmount()*PLATFORM_COMMISION;

        walletService.deductMoneyFromWallet(driver.getUser(), platformCommision, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
