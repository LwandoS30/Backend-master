package com.relink.backend.controller;

import com.relink.backend.dto.LoginRequest;
import com.relink.backend.model.Users;
import com.relink.backend.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {

    private Users user;
    @Autowired
    private JWTService jwtService;

    private  final AuthenticationManager authenticationManager;
    //private  final JwtService jwtService;
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(), loginRequest.getPassword())
        );
        if (authentication.isAuthenticated()){
//            return ResponseEntity.ok("Logged in Successfully");
            return jwtService.generateToken(user.getEmail());
        }
        return String.valueOf(ResponseEntity.status(401).body("Invalid credentials"));
    }
}
