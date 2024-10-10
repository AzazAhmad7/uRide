package com.project.uRide.services.Impl;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.SignUpDTO;
import com.project.uRide.dtos.UserDTO;
import com.project.uRide.entities.User;
import com.project.uRide.entities.Wallet;
import com.project.uRide.entities.enums.Role;
import com.project.uRide.exceptions.RuntimeConflictException;
import com.project.uRide.repository.UserRepository;
import com.project.uRide.services.AuthService;
import com.project.uRide.services.RiderService;
import com.project.uRide.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        if(userRepository.findByEmail(signUpDTO.getEmail()).orElse(null) != null){
            throw new RuntimeConflictException("User already exists with email: " + signUpDTO.getEmail());
        }
        User user = modelMapper.map(signUpDTO, User.class);
        user.setRoles(Set.of(Role.RIDER));

        User savedUser = userRepository.save(user);
        riderService.createNewRider(user);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onBoardDriver(Long userId) {
        return null;
    }
}
