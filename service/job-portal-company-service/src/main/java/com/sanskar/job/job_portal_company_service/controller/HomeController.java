package com.sanskar.job.job_portal_company_service.controller;

import com.sanskar.job.domain.UserRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class HomeController {

    @GetMapping("/health")
    public String home() {
        return "Company service working - "+ UserRole.ROLE_EMPLOYER;
    }
}
