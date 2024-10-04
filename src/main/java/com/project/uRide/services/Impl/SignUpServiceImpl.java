package com.project.uRide.services.Impl;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.SignUpDTO;
import com.project.uRide.dtos.UserDTO;
import com.project.uRide.services.SignupService;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignupService {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        return null;
    }

    @Override
    public DriverDTO onBoardDriver(Long userId) {
        return null;
    }
}
