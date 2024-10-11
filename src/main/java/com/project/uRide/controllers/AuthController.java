package com.project.uRide.controllers;

import com.project.uRide.dtos.DriverDTO;
import com.project.uRide.dtos.SignUpDTO;
import com.project.uRide.dtos.UserDTO;
import com.project.uRide.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        return ResponseEntity.ok(modelMapper.map(authService.signUp(signUpDTO), UserDTO.class));
    }

    @PostMapping("/onboardDriver/{userId}")
    public ResponseEntity<DriverDTO> onboardNewDriver(@PathVariable Long userId){
        return ResponseEntity.ok(authService.onBoardDriver(userId));
    }
}
