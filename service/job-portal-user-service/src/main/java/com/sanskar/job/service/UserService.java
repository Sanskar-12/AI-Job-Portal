package com.sanskar.job.service;

import com.sanskar.job.dto.response.UserResponse;
import com.sanskar.job.model.User;
import com.sanskar.job.payload.UpdateUserRequest;

import java.util.List;

public interface UserService {

    // all users action
    User getUserByEmail(String email) throws Exception;

    User getUserById(Long id) throws Exception;

    List<User> getAllUsers();

    UserResponse updateProfile(String email, UpdateUserRequest req) throws Exception;

    // admin actions
    UserResponse suspendUser(Long id) throws Exception;

    UserResponse activateUser(Long id) throws Exception;

    UserResponse deleteUser(Long id) throws Exception;
}
