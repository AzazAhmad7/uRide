package com.project.uRide.services.Impl;

import com.project.uRide.dtos.WalletTransactionDTO;
import com.project.uRide.entities.Wallet;
import com.project.uRide.entities.WalletTransaction;
import com.project.uRide.repository.WalletTransactionRepository;
import com.project.uRide.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransactionDTO walletTransactionDTO) {
        WalletTransaction walletTransaction = modelMapper.map(walletTransactionDTO, WalletTransaction.class);
        walletTransactionRepository.save(walletTransaction);
    }
}
