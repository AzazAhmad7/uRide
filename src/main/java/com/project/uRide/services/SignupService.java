package com.project.uRide.services;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.SignUpDTO;
import com.project.uRide.dtos.UserDTO;

public interface SignupService {
    String login(String email, String password);
    UserDTO signUp(SignUpDTO signUpDTO);
    DriverDTO onBoardDriver(Long userId);
}
