package com.sanskar.job.job_portal_resume_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume")
public class HomeController {

    @GetMapping
    public ResponseEntity<ApiResponse> Home() {
        ApiResponse response=new ApiResponse();
        response.setMessage("Service for managing candidate resumes");
        response.setStatus(true);

        return ResponseEntity.ok(response);
    }
}
