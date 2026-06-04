package com.sanskar.job.dto.response;

import com.sanskar.job.domain.UserRole;
import com.sanskar.job.domain.UserStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String profileImage;
    private UserRole role;
    private UserStatus status;
    private Boolean verified;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
}
