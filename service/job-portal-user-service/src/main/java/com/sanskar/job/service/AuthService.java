package com.sanskar.job.service;

import com.sanskar.job.payload.AuthResponse;
import com.sanskar.job.payload.LoginRequest;
import com.sanskar.job.payload.SignUpRequest;

public interface AuthService {

    AuthResponse signUp(SignUpRequest req) throws Exception;

    AuthResponse login(LoginRequest req);
}
