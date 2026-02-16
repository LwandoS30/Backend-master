package com.relink.backend.service;

import com.relink.backend.dto.LoginRequest;
import com.relink.backend.dto.RegisterRequestDto;
import com.relink.backend.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    RegisterRequestDto createUser(RegisterRequestDto userDto);

    RegisterRequestDto getUserById(Long userId);

    List<RegisterRequestDto> getAllUsers();

    RegisterRequestDto updateUser(Long userId, RegisterRequestDto updateUser);

    void deleteUser(Long userId);

}
