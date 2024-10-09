package com.project.uRide.services.Impl;

import com.project.uRide.entities.User;
import com.project.uRide.entities.Wallet;
import com.project.uRide.exceptions.ResourceNotFoundException;
import com.project.uRide.repository.WalletRepository;
import com.project.uRide.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;

    @Override
    public Wallet addMoneyToWallet(User user, double amount) {
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("Wallet not found"));
        wallet.setBalance(wallet.getBalance() + amount);
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
}
