package com.project.uRide.services;

import com.project.uRide.entities.Driver;
import com.project.uRide.entities.Ride;
import com.project.uRide.entities.User;
import com.project.uRide.entities.Wallet;
import com.project.uRide.entities.enums.TransactionMethod;
import com.project.uRide.entities.enums.TransactionType;

public interface WalletService {
    Wallet addMoneyToWallet(User user, double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    Wallet deductMoneyFromWallet(User user, double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
    void withdrawAllMoneyFromWallet();
    Wallet findWalletById(Long id);
    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
