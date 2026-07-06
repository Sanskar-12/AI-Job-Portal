package com.sanskar.job.job_portal_resume_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.ResumeSkillResponse;
import com.sanskar.job.job_portal_resume_service.payload.AddResumeSkillRequest;
import com.sanskar.job.job_portal_resume_service.service.ResumeSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes/{resumeId}/resumeskill")
public class ResumeSkillController {

    private final ResumeSkillService resumeSkillService;

    @PostMapping("/create")
    public ResponseEntity<ResumeSkillResponse> addSkill(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddResumeSkillRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeSkillService.addSkill(resumeId,candidateId,request));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ResumeSkillResponse>> getSkills(@PathVariable Long resumeId) {
        return ResponseEntity.ok(resumeSkillService.getSkills(resumeId));
    }

    @PutMapping("/{skillId}")
    public ResponseEntity<ResumeSkillResponse> updateSkill(
            @PathVariable Long resumeId,
            @PathVariable Long skillId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddResumeSkillRequest request
    ) throws Exception {
        return ResponseEntity.ok(resumeSkillService.updateSkill(resumeId,skillId,candidateId,request));
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<ApiResponse> deleteSkill(
            @PathVariable Long resumeId,
            @PathVariable Long skillId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        resumeSkillService.deleteSkill(skillId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Skill Deleted Successfully",true));
    }
}
