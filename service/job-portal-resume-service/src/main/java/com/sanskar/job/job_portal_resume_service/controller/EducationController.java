package com.sanskar.job.job_portal_resume_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.EducationResponse;
import com.sanskar.job.job_portal_resume_service.payload.AddEducationRequest;
import com.sanskar.job.job_portal_resume_service.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/resumes/{resumeId}/education")
public class EducationController {

    private final EducationService educationService;

    @PostMapping("/create")
    public ResponseEntity<EducationResponse> adddEducation(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddEducationRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(educationService.addEducation(resumeId,candidateId,request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<EducationResponse>> getEducation(@PathVariable Long resumeId) {
        return ResponseEntity.ok(educationService.getEducations(resumeId));
    }

    @PutMapping("/{educationId}")
    public ResponseEntity<EducationResponse> updateEducation(
            @PathVariable Long resumeId,
            @PathVariable Long educationId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddEducationRequest request
    ) throws Exception {
        return ResponseEntity.ok(educationService.updateEducation(resumeId,educationId,candidateId,request));
    }

    @DeleteMapping("/{educationId}")
    public ResponseEntity<ApiResponse> deleteEducation(
            @PathVariable Long resumeId,
            @PathVariable Long educationId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        educationService.deleteEducation(educationId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Education Deleted Successfully",true));
    }
}
