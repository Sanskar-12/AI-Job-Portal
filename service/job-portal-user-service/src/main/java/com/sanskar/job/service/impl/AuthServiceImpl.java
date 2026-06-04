package com.sanskar.job.service.impl;

import com.sanskar.job.domain.UserRole;
import com.sanskar.job.domain.UserStatus;
import com.sanskar.job.mapper.UserMapper;
import com.sanskar.job.model.User;
import com.sanskar.job.payload.AuthResponse;
import com.sanskar.job.payload.LoginRequest;
import com.sanskar.job.payload.SignUpRequest;
import com.sanskar.job.repository.UserRepository;
import com.sanskar.job.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse signUp(SignUpRequest req) throws Exception {
        if(userRepository.existsByEmail(req.getEmail())) {
            throw new Exception("Email Already registered : "+req.getEmail());
        }

        if(req.getRole()== UserRole.ROLE_ADMIN) {
            throw new Exception("Cannot self-registered as role Admin");
        }

        User user=User.builder()
                .fullName(req.getFullName())
                .email(req.getEmail())
                .password(req.getPassword())
                .role(req.getRole())
                .phone(req.getPhone())
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser=userRepository.save(user);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setTitle("Welcome "+savedUser.getFullName());
        authResponse.setMessage("Registered Successfully");
        authResponse.setJwt("jwt");
        authResponse.setUser(UserMapper.toDTO(savedUser));


        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest req) {
        return null;
    }
}
