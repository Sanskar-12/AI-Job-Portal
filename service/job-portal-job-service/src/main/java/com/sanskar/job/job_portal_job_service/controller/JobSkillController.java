package com.sanskar.job.job_portal_job_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.JobSkillResponse;
import com.sanskar.job.job_portal_job_service.payload.JobSkillRequest;
import com.sanskar.job.job_portal_job_service.service.JobSkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-skills")
public class JobSkillController {

    private final JobSkillService jobSkillService;

    @PostMapping("/create")
    public ResponseEntity<JobSkillResponse> createSkill(@RequestBody @Valid JobSkillRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobSkillService.createSkill(request));
    }

    public ResponseEntity<List<JobSkillResponse>> getAllSkills() throws Exception {
        return ResponseEntity.ok(jobSkillService.getAllSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponse> getSkillById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(jobSkillService.getSkillById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponse> updateSkill(@PathVariable Long id,@RequestBody @Valid JobSkillRequest request) throws Exception {
        return ResponseEntity.ok(jobSkillService.updateSkill(id,request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSkill(@PathVariable Long id) throws Exception {
        jobSkillService.deleteSkill(id);
        return ResponseEntity.ok(new ApiResponse("Skill Deleted Successfully",true));
    }
}
