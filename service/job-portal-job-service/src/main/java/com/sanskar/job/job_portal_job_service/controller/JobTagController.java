package com.sanskar.job.job_portal_job_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.JobTagResponse;
import com.sanskar.job.job_portal_job_service.mapper.JobTagRequest;
import com.sanskar.job.job_portal_job_service.service.JobTagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-tags")
public class JobTagController {

    private final JobTagService jobTagService;

    @PostMapping("/create")
    public ResponseEntity<JobTagResponse> createJobTag(@RequestBody @Valid JobTagRequest jobTagRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobTagService.createTag(jobTagRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobTagResponse>> getAllTags() {
        return ResponseEntity.ok(jobTagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getTagById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobTagService.getTagById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> updateTag(@PathVariable Long id, @RequestBody @Valid JobTagRequest jobTagRequest) throws Exception {
        return ResponseEntity.ok(jobTagService.updateTag(id,jobTagRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTag(@PathVariable Long id) throws Exception {
        jobTagService.deleteTag(id);
        return ResponseEntity.ok(new ApiResponse("Tag Deleted Successfully",true));
    }
}
