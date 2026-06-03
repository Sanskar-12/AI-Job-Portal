package com.sanskar.job.controller;

import com.sanskar.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String HomeController() {
        return "Job Portal"+ UserRole.ROLE_JOB_SEEKER;
    }
}
