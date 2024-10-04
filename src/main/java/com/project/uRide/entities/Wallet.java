package com.project.uRide.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    private Double Balance;

    @OneToMany(mappedBy = "wallet")
    private List<WalletTransaction> transactions;
}
