package com.sanskar.job.job_portal_job_service.controller;

import com.sanskar.job.dto.request.JobRequest;
import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.JobResponse;
import com.sanskar.job.job_portal_job_service.payload.JobSearchRequest;
import com.sanskar.job.job_portal_job_service.service.JobService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<JobResponse> createJob(@RequestHeader("X-User-Id") Long employerId,  @RequestBody @Valid JobRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.createJob(employerId,request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<JobResponse>> getJobs(@ModelAttribute JobSearchRequest request) throws Exception {
        return ResponseEntity.ok(jobService.getJobs(request));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<JobResponse>> getJobsByCompany(@PathVariable Long companyId) throws Exception {
        return ResponseEntity.ok(jobService.getJobsByCompany(companyId));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<JobResponse>> getAllJobsAdmin() throws Exception {
        return ResponseEntity.ok(jobService.getAllJobsAdmin());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId,
            @RequestBody @Valid JobRequest request
    ) throws Exception {
        return ResponseEntity.ok(jobService.updateJob(id,employerId,request));
    }

    @PatchMapping("/{id}/publish")
    public ResponseEntity<JobResponse> publishJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        return ResponseEntity.ok(jobService.publishJob(id,employerId));
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<JobResponse> closeJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        return ResponseEntity.ok(jobService.closeJob(id,employerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteJob(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long employerId
    ) throws Exception {
        jobService.deleteJob(id,employerId);
        return ResponseEntity.ok(new ApiResponse("Job Deleted Successfully",true));
    }

    @GetMapping("/{id}")
    @Transactional()
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
}
