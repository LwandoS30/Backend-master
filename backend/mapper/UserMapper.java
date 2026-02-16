package com.relink.backend.mapper;

import com.relink.backend.dto.RegisterRequestDto;
import com.relink.backend.model.Users;

public class UserMapper {
    public static RegisterRequestDto mapToRegisterRequest(Users user){
        return new RegisterRequestDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword()
        );
    }

    public static Users mapToUser(RegisterRequestDto dto) {
        Users user = new Users();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole() != null ? dto.getRole() : "USER");

        return user;
    }
}
