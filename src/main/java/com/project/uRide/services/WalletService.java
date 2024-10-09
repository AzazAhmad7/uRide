package com.project.uRide.services;

import com.project.uRide.entities.User;
import com.project.uRide.entities.Wallet;

public interface WalletService {
    Wallet addMoneyToWallet(User user, double amount);
    void withdrawAllMoneyFromWallet();
    Wallet findWalletById(Long id);
    Wallet createNewWallet(User user);
}
