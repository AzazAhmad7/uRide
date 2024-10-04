package com.project.uRide.entities;

import com.project.uRide.entities.enums.TransactionMethod;
import com.project.uRide.entities.enums.TransactionType;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMethod transactionMethod;

    @ManyToOne
    private Wallet wallet;

    @OneToOne
    private Ride ride;
    private String transactionId;
    @CreationTimestamp
    private LocalDateTime timestamp;
}
