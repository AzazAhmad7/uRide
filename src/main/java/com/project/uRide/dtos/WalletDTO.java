package com.project.uRide.dtos;

import com.project.uRide.entities.User;
import com.project.uRide.entities.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDTO {
    private Long id;
    private UserDTO user;
    private Double Balance;
    private List<WalletTransactionDTO> transactions;
}
