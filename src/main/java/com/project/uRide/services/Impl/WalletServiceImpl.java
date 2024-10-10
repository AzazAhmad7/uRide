package com.project.uRide.services.Impl;

import com.project.uRide.entities.*;
import com.project.uRide.entities.enums.TransactionMethod;
import com.project.uRide.entities.enums.TransactionType;
import com.project.uRide.exceptions.ResourceNotFoundException;
import com.project.uRide.repository.WalletRepository;
import com.project.uRide.services.WalletService;
import com.project.uRide.services.WalletTransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionService walletTransactionService;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() + amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .wallet(wallet)
                .amount(amount)
                .ride(ride)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() - amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .wallet(wallet)
                .amount(amount)
                .ride(ride)
                .build();

        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet Can not be found with id "+id));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = Wallet.builder()
                .user(user)
                .Balance(0.0)
                .build();
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("Wallet not found"));
    }
}
