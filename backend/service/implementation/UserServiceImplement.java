package com.relink.backend.service.implementation;

import com.relink.backend.dto.LoginRequest;
import com.relink.backend.dto.RegisterRequestDto;
import com.relink.backend.exception.ResourceNotFoundException;
import com.relink.backend.mapper.UserMapper;
import com.relink.backend.model.UserPrincipal;
import com.relink.backend.model.Users;
import com.relink.backend.repository.UserRepository;
import com.relink.backend.service.JWTService;
import com.relink.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/*The following annotation tells the spring container
 to create the spring beam for this class*/
@Service

public class UserServiceImplement implements UserService, UserDetailsService {

    //Injecting the dependencies before implementing the below methods

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    AuthenticationManager authManager;

    public UserServiceImplement(UserRepository userRepository,
                                PasswordEncoder passwordEncoder, JWTService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterRequestDto createUser(RegisterRequestDto userDto) {
        /*We need to store user entity into a database
        And user to RegisterRequestDto */
        Users user = UserMapper.mapToUser(userDto);
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Users savedUser = userRepository.save(user);

        RegisterRequestDto respondDto = new RegisterRequestDto();
        respondDto.setId(savedUser.getId());
        respondDto.setEmail(savedUser.getEmail());

        return respondDto;
    }

    @Override
    public RegisterRequestDto getUserById(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User with ID: "+ userId
                                +" does not exist")
                );
        return UserMapper.mapToRegisterRequest(user);
    }

    @Override
    public List<RegisterRequestDto> getAllUsers() {
        List<Users> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToRegisterRequest)
                .collect(Collectors.toList());
    }

    @Override
    public RegisterRequestDto updateUser(Long userId, RegisterRequestDto updateUser) {

        Users user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User with ID: "+ userId + "does not exist")
        );

        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setEmail(updateUser.getEmail());

        if(updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        }

        Users updatedUser = userRepository.save(user);
        return UserMapper.mapToRegisterRequest(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        Users user = userRepository.findById(userId).orElseThrow(() -> new
                ResourceNotFoundException("User with ID: "+ userId +" does not exist")
        );
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);

        if(user == null){
                System.out.println("User not found ");
                throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
