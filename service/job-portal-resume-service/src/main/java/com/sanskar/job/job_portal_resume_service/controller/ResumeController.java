package com.sanskar.job.job_portal_resume_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.PersonalInfoResponse;
import com.sanskar.job.dto.response.ResumeResponse;
import com.sanskar.job.job_portal_resume_service.payload.CreateResumeRequest;
import com.sanskar.job.job_portal_resume_service.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/create")
    public ResponseEntity<ResumeResponse> createResume(@RequestHeader("X-User-Id") Long candidateId, @RequestBody @Valid CreateResumeRequest request) {
        return ResponseEntity.ok(resumeService.createResume(candidateId,request));
    }

    @GetMapping("/my")
    public ResponseEntity<List<ResumeResponse>> getMyResume(@RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(resumeService.getMyResumes(candidateId));
    }

    @PutMapping("/{resumeId}/personal-info")
    public ResponseEntity<ResumeResponse> updatePersonalInfo(@PathVariable Long resumeId, @RequestHeader("X-User-Id") Long candidateId, @RequestBody @Valid PersonalInfoResponse req
    ) throws Exception {
        return ResponseEntity.ok(resumeService.updatePersonalInfo(resumeId,candidateId,req));
    }

    @PutMapping("/{resumeId}/summary")
    public ResponseEntity<ResumeResponse> updateSummary(@PathVariable Long resumeId, @RequestHeader("X-User-Id") Long candidateId, @RequestParam String summary
    ) throws Exception {
        return ResponseEntity.ok(resumeService.updateSummary(resumeId,candidateId,summary));
    }

    @PatchMapping("/{resumeId}/set-default")
    public ResponseEntity<ResumeResponse> setDefaultResume(@PathVariable Long resumeId,@RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(resumeService.setDefaultResume(resumeId,candidateId));
    }

    @DeleteMapping("/{resumeId}")
    public ResponseEntity<ApiResponse> deleteResume(@PathVariable Long resumeId, @RequestHeader("X-User-Id") Long candidateId) throws Exception {
        resumeService.deleteResume(resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Resume Deleted Successfully",true));
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity<ResumeResponse> getResumeById(@PathVariable Long resumeId,@RequestHeader("X-User-Id") Long candidateId) throws Exception {
        return ResponseEntity.ok(resumeService.getResumeById(resumeId,candidateId));
    }
}
