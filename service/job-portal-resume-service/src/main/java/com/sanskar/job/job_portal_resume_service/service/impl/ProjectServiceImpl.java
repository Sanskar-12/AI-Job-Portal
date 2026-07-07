package com.sanskar.job.job_portal_resume_service.service.impl;

import com.sanskar.job.dto.response.ProjectResponse;
import com.sanskar.job.job_portal_resume_service.mapper.ResumeMapper;
import com.sanskar.job.job_portal_resume_service.model.Project;
import com.sanskar.job.job_portal_resume_service.model.Resume;
import com.sanskar.job.job_portal_resume_service.payload.AddProjectRequest;
import com.sanskar.job.job_portal_resume_service.repository.ProjectRepository;
import com.sanskar.job.job_portal_resume_service.service.ProjectService;
import com.sanskar.job.job_portal_resume_service.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ResumeService resumeService;
    private final ProjectRepository projectRepository;

    @Override
    public ProjectResponse addProject(Long resumeId, Long candidateId, AddProjectRequest request) throws Exception {
        Resume resume=resumeService.getResumeEntity(resumeId);
        assertOwner(resume,candidateId);

        Project project=Project.builder()
                .resume(resume)
                .title(request.getTitle())
                .description(request.getDescription())
                .technologies(request.getTechnologies()!=null ? request.getTechnologies() : List.of())
                .projectUrl(request.getProjectUrl())
                .sourceCodeUrl(request.getSourceCodeUrl())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isOngoing(Boolean.TRUE.equals(request.getIsOngoing()))
                .displayOrder(request.getDisplayOrder()!=null ? request.getDisplayOrder() : 0)
                .build();

        Project savedProject=projectRepository.save(project);

        return ResumeMapper.toProjectResponse(savedProject);
    }

    @Override
    public List<ProjectResponse> getAllProjects(Long resumeId) {
        return projectRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId).stream().map(ResumeMapper::toProjectResponse).toList();
    }

    @Override
    public ProjectResponse updateProject(Long projectId, Long resumeId, Long candidateId, AddProjectRequest request) throws Exception {
        Project project=getProjectEntity(projectId);
        assertOwner(project.getResume(),candidateId);

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        if(request.getTechnologies()!=null) {
            project.setTechnologies(request.getTechnologies());
        }
        project.setProjectUrl(request.getProjectUrl());
        project.setSourceCodeUrl(request.getSourceCodeUrl());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setIsOngoing(Boolean.TRUE.equals(request.getIsOngoing()));
        if(request.getDisplayOrder()!=null) {
            project.setDisplayOrder(request.getDisplayOrder());
        }

        Project updatedProject=projectRepository.save(project);

        return ResumeMapper.toProjectResponse(updatedProject);
    }

    @Override
    public void deleteProject(Long projectId, Long resumeId, Long candidateId) throws Exception {
        Project project=getProjectEntity(projectId);
        assertOwner(project.getResume(),candidateId);

        projectRepository.delete(project);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Resume not found");
        }
    }

    private Project getProjectEntity(Long projectId) throws Exception {
        return projectRepository.findById(projectId).orElseThrow(()->new Exception("Project not found"));
    }
}
