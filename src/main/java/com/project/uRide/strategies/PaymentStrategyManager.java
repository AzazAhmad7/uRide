package com.project.uRide.strategies;

import com.project.uRide.entities.enums.PaymentMethod;
import com.project.uRide.strategies.impl.CashPaymentStrategy;
import com.project.uRide.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy getPaymentStrategy(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case WALLET:
                return walletPaymentStrategy;
            case CASH:
                return cashPaymentStrategy;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }
}
