package com.project.uRide.services;

import com.project.uRide.dtos.WalletTransactionDTO;
import com.project.uRide.entities.WalletTransaction;

public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransactionDTO walletTransactionDTO);
}
