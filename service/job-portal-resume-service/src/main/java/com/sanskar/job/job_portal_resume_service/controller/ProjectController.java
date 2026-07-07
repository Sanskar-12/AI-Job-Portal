package com.sanskar.job.job_portal_resume_service.controller;

import com.sanskar.job.dto.response.ApiResponse;
import com.sanskar.job.dto.response.ProjectResponse;
import com.sanskar.job.job_portal_resume_service.payload.AddProjectRequest;
import com.sanskar.job.job_portal_resume_service.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resumes/{resumeId}/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<ProjectResponse> addProject(
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddProjectRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.addProject(resumeId,candidateId,request));
    };

    @GetMapping("/get/projects")
    public ResponseEntity<List<ProjectResponse>> getAllProjects(
            @PathVariable Long resumeId
    ) {
        return ResponseEntity.ok(projectService.getAllProjects(resumeId));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long projectId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId,
            @RequestBody @Valid AddProjectRequest request
    ) throws Exception {
        return ResponseEntity.ok(projectService.updateProject(projectId,resumeId,candidateId,request));
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponse> deleteProject(
            @PathVariable Long projectId,
            @PathVariable Long resumeId,
            @RequestHeader("X-User-Id") Long candidateId
    ) throws Exception {
        projectService.deleteProject(projectId,resumeId,candidateId);
        return ResponseEntity.ok(new ApiResponse("Project deleted successfully",true));
    }

}
