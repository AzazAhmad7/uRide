package com.project.uRide.controllers;

import com.project.uRide.dtos.*;
import com.project.uRide.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;


@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final ModelMapper modelMapper;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        return ResponseEntity.ok(modelMapper.map(authService.signUp(signUpDTO), UserDTO.class));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/onboardDriver/{userId}")
    public ResponseEntity<DriverDTO> onboardNewDriver(@PathVariable Long userId){
        return ResponseEntity.ok(authService.onBoardDriver(userId));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginIn(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest request, HttpServletResponse response){

        String tokens[] = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        Cookie cookie = new Cookie("token", tokens[1]);
        cookie.setHttpOnly(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }
}
