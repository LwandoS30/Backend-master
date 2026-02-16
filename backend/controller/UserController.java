package com.relink.backend.controller;

import com.relink.backend.dto.LoginRequest;
import com.relink.backend.dto.RegisterRequestDto;
import com.relink.backend.model.Users;
import com.relink.backend.service.JWTService;
import com.relink.backend.service.TokenBlacklistService;
import com.relink.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
//This controller class is capable of handling http request/ is built to handle http requests
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final TokenBlacklistService tokenBlacklistService;
    //Build add user REST API
    //@PostMapping annotation is used by the below method to map http post request

    //
    @PostMapping("/register")
    public ResponseEntity<RegisterRequestDto> createUser(@RequestBody RegisterRequestDto userDto){
        RegisterRequestDto savedUser = userService.createUser(userDto);
        //RETURNS 201 created (into a database)
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){

        try {
            //Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            //Check results of authentication for success or failure
            if(authentication.isAuthenticated()){

                String token = jwtService.generateToken(loginRequest.getEmail());
                return ResponseEntity.ok(token);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication Failed");

        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid login credentials");
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader){
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            tokenBlacklistService.blacklistToken(token);

            return ResponseEntity.ok("Logged out successfully!");
        }

        return ResponseEntity.badRequest().body("Invalid token");
    }

    @GetMapping("{id}")
    public ResponseEntity<RegisterRequestDto> getUserById(@PathVariable("id") Long userId){
        RegisterRequestDto userDto = userService.getUserById(userId);
         return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<RegisterRequestDto>> getAllUsers(){
        List<RegisterRequestDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @PutMapping("{id}")
    public ResponseEntity<RegisterRequestDto> updateUser(@PathVariable("id") Long userId,
                                                         @RequestBody RegisterRequestDto updateUser){

        RegisterRequestDto userDto = userService.updateUser(userId, updateUser);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);

        return ResponseEntity.ok("User with ID: "+ userId +" is deleted successfully!");
    }
}
