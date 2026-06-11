package com.sanskar.job.job_portal_job_service.controller;

import com.sanskar.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String HomeController() {
        return "Service for managing job postings "+UserRole.ROLE_EMPLOYER;
    }
}
