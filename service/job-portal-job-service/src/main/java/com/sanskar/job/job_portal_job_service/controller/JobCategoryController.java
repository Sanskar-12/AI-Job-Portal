package com.sanskar.job.job_portal_job_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.JobCategoryResponse;
import com.sanskar.job.job_portal_job_service.payload.JobCategoryRequest;
import com.sanskar.job.job_portal_job_service.service.JobCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-categories")
public class JobCategoryController {

    private final JobCategoryService jobCategoryService;

    @PostMapping("/create")
    public ResponseEntity<JobCategoryResponse> createCategory(@RequestBody @Valid JobCategoryRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCategoryService.createCategory(request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<JobCategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(jobCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> getCategoryById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobCategoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobCategoryResponse> updateCategory(@PathVariable Long id,@RequestBody @Valid JobCategoryRequest request) throws Exception {
        return ResponseEntity.ok(jobCategoryService.updateCategory(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws Exception {
        jobCategoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Category Deleted Successfully",true));
    }
}
