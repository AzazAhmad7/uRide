package com.project.uRide.services.Impl;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.SignUpDTO;
import com.project.uRide.dtos.UserDTO;
import com.project.uRide.entities.Driver;
import com.project.uRide.entities.User;
import com.project.uRide.entities.Wallet;
import com.project.uRide.entities.enums.Role;
import com.project.uRide.exceptions.ResourceNotFoundException;
import com.project.uRide.exceptions.RuntimeConflictException;
import com.project.uRide.repository.UserRepository;
import com.project.uRide.security.JWTService;
import com.project.uRide.services.AuthService;
import com.project.uRide.services.DriverService;
import com.project.uRide.services.RiderService;
import com.project.uRide.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.project.uRide.entities.enums.Role.DRIVER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        User user = (User) auth.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[]{accessToken, refreshToken};
    }

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        if(userRepository.findByEmail(signUpDTO.getEmail()).orElse(null) != null){
            throw new RuntimeConflictException("User already exists with email: " + signUpDTO.getEmail());
        }
        User user = modelMapper.map(signUpDTO, User.class);
        user.setRoles(Set.of(Role.RIDER));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        riderService.createNewRider(user);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onBoardDriver(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id "+userId));
        if(user.getRoles().contains(DRIVER)){
            throw new RuntimeException("User with id "+userId+" is already a driver");
        }
        Driver newDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .available(true)
                .build();

        user.getRoles().add(DRIVER);
        userRepository.save(user);

        return modelMapper.map(driverService.createNewDriver(newDriver), DriverDTO.class);
    }
}
