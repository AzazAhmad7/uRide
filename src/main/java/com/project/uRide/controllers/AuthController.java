package com.project.uRide.controllers;

import com.project.uRide.dtos.SignUpDTO;
import com.project.uRide.dtos.UserDTO;
import com.project.uRide.entities.User;
import com.project.uRide.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    public UserDTO signUp(@RequestBody SignUpDTO signUpDTO){
        return modelMapper.map(authService.signUp(signUpDTO), UserDTO.class);
    }
}
