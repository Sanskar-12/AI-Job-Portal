package com.sanskar.job.service.impl;

import com.sanskar.job.domain.UserRole;
import com.sanskar.job.domain.UserStatus;
import com.sanskar.job.mapper.UserMapper;
import com.sanskar.job.model.User;
import com.sanskar.job.payload.AuthResponse;
import com.sanskar.job.payload.LoginRequest;
import com.sanskar.job.payload.SignUpRequest;
import com.sanskar.job.repository.UserRepository;
import com.sanskar.job.security.CustomUserDetailsService;
import com.sanskar.job.security.JwtProvider;
import com.sanskar.job.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;

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
                .password(passwordEncoder.encode(req.getPassword()))
                .role(req.getRole())
                .phone(req.getPhone())
                .lastLogin(LocalDateTime.now())
                .status(UserStatus.ACTIVE)
                .build();

        User savedUser=userRepository.save(user);

        Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication, String.valueOf(savedUser.getId()));


        AuthResponse authResponse=new AuthResponse();
        authResponse.setTitle("Welcome "+savedUser.getFullName());
        authResponse.setMessage("Registered Successfully");
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(savedUser));


        return authResponse;
    }

    @Override
    public AuthResponse login(LoginRequest req) throws Exception {

        Authentication authentication=authenticate(req.getEmail(),req.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user=userRepository.findByEmail(req.getEmail());

        String jwt=jwtProvider.generateToken(authentication, String.valueOf(user.getId()));

        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setTitle("Welcome Back "+user.getFullName());
        authResponse.setMessage("Logged In Successfully");
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));

        return authResponse;
    }

    private Authentication authenticate(String email,String password) throws Exception {
        UserDetails userDetails=customUserDetailsService.loadUserByUsername(email);

        if(userDetails==null) {
            throw new Exception("User not found with email "+email);
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exception("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
