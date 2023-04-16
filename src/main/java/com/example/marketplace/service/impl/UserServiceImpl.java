package com.example.marketplace.service.impl;

import com.example.marketplace.config.JwtGenerator;
import com.example.marketplace.exception.domain.UserNotFoundByPhoneNumberException;
import com.example.marketplace.model.dto.*;
import com.example.marketplace.model.entity.User;
import com.example.marketplace.model.enums.Role;
import com.example.marketplace.repository.UserRepository;
import com.example.marketplace.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static com.example.marketplace.constants.validation.UserValidationConstants.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;


    @Autowired
    @Lazy
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,
                           JwtGenerator jwtGenerator,AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.modelMapper=new ModelMapper();
        this.passwordEncoder=passwordEncoder;
        this.jwtGenerator=jwtGenerator;
        this.authenticationManager=authenticationManager;


    }




    @Override
    public AuthResponseDto login(LoginDto loginDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getPhoneNumber(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtGenerator.generateToken(authentication);
        return new AuthResponseDto(token);
    }

    @Override
    public String registerUser(RegisterDto registerDto){
        if(userRepository.existsByPhoneNumber(registerDto.getPhoneNumber())){
             throw new RuntimeException(USER_PHONE_NUMBER_ALREADY_EXISTS_MESSAGE);
        }
        if(!registerDto.getPassword().equals(registerDto.getMatchingPassword())){
            throw  new RuntimeException(USER_PASSWORDS_NOT_EQUAL_MESSAGE);
        }
        User user=modelMapper.map(registerDto,User.class);
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.USER);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastname());
        user.setPhoneNumber(registerDto.getPhoneNumber());
        userRepository.save(user);
        return "Register is success";
    }


    @Override
    public UserDetails loadUserByUsername(String username)  {
        return userRepository.getByPhoneNumber(username).orElseThrow(UserNotFoundByPhoneNumberException::new);
    }
}
