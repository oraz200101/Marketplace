package com.example.marketplace.service;

import com.example.marketplace.model.dto.*;
import com.example.marketplace.model.entity.User;
import com.example.marketplace.service.base.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends  UserDetailsService {
    AuthResponseDto login(LoginDto loginDto);

    String registerUser(RegisterDto registerDto);
}
