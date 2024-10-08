package com.project.uRide.strategies.impl;

import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Payment;
import com.project.uRide.entities.Rider;
import com.project.uRide.entities.Wallet;
import com.project.uRide.entities.enums.PaymentStatus;
import com.project.uRide.entities.enums.TransactionMethod;
import com.project.uRide.entities.enums.TransactionType;
import com.project.uRide.repository.PaymentRepository;
import com.project.uRide.repository.WalletRepository;
import com.project.uRide.services.PaymentService;
import com.project.uRide.services.WalletService;
import com.project.uRide.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Rider rider = payment.getRide().getRider();
        Driver driver = payment.getRide().getDriver();

        double driversCut = payment.getAmount()*(1-PLATFORM_COMMISION);

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);
        walletService.addMoneyToWallet(driver.getUser(), driversCut,null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
