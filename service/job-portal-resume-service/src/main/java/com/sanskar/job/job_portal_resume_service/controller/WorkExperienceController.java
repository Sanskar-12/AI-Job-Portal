package com.sanskar.job.job_portal_resume_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.WorkExperienceResponse;
import com.sanskar.job.job_portal_resume_service.payload.AddWorkExperienceRequest;
import com.sanskar.job.job_portal_resume_service.service.WorkExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes/{resumeId}/work-experiences")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;

    @PostMapping("/create")
    public ResponseEntity<WorkExperienceResponse> addWorkExperience(@PathVariable Long resumeId, @RequestHeader("X-User-Id") Long candidateId, @RequestBody @Valid AddWorkExperienceRequest request) throws Exception {
        return ResponseEntity.ok(workExperienceService.addWorkExperience(resumeId,candidateId,request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<WorkExperienceResponse>> getWorkExperience(@PathVariable Long resumeId) throws Exception {
        return ResponseEntity.ok(workExperienceService.getWorkExperiences(resumeId));
    }

    @PutMapping("/{experienceId}")
    public ResponseEntity<WorkExperienceResponse> updateWorkExperience(@PathVariable Long resumeId,@PathVariable Long experienceId,@RequestBody @Valid AddWorkExperienceRequest request,@RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(workExperienceService.updateWorkExperience(resumeId,experienceId,request,candidateId));
    }

    @DeleteMapping("/{experienceId}")
    public ResponseEntity<ApiResponse> deleteWorkExperience(@PathVariable Long resumeId, @PathVariable Long experienceId, @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        workExperienceService.deleteWorkExperience(resumeId, experienceId, candidateId);
        return ResponseEntity.ok(new ApiResponse("Work Experience Deleted Successfully",true));
    }
}
