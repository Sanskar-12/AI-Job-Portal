package com.sanskar.job.job_portal_resume_service.service.impl;

import com.sanskar.job.dto.response.WorkExperienceResponse;
import com.sanskar.job.job_portal_resume_service.mapper.WorkExperienceMapper;
import com.sanskar.job.job_portal_resume_service.model.Resume;
import com.sanskar.job.job_portal_resume_service.model.WorkExperience;
import com.sanskar.job.job_portal_resume_service.payload.AddWorkExperienceRequest;
import com.sanskar.job.job_portal_resume_service.repository.WorkExperienceRepository;
import com.sanskar.job.job_portal_resume_service.service.ResumeService;
import com.sanskar.job.job_portal_resume_service.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepository workExperienceRepository;
    private final ResumeService resumeService;

    @Override
    public WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest request) throws Exception {
        Resume resume=resumeService.getResumeEntity(resumeId);
        assertOwner(resume,candidateId);

        WorkExperience workExperience=WorkExperience.builder()
                .resume(resume)
                .companyName(request.getCompanyName())
                .companyLogoUrl(request.getCompanyLogoUrl())
                .jobTitle(request.getJobTitle())
                .employmentType(request.getEmploymentType())
                .location(request.getLocation())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentJob(Boolean.TRUE.equals(request.getIsCurrentJob()))
                .description(request.getDescription())
                .technologies(request.getTechnologies()!=null ? request.getTechnologies() : List.of())
                .displayOrder(request.getDisplayOrder()!=null ? request.getDisplayOrder() : 0)
                .build();

        WorkExperience savedWorkExp=workExperienceRepository.save(workExperience);

        return WorkExperienceMapper.toWorkExperience(savedWorkExp);
    }

    @Override
    public List<WorkExperienceResponse> getWorkExperiences(Long resumeId) {
        return workExperienceRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId).stream().map(WorkExperienceMapper::toWorkExperience).toList();
    }

    @Override
    public WorkExperienceResponse updateWorkExperience(Long resumeId, Long workExperienceId, AddWorkExperienceRequest request, Long candidateId) throws Exception {
        WorkExperience workExperience=getWorkExperienceEntity(workExperienceId);

        assertOwner(workExperience.getResume(),candidateId);

        workExperience.setCompanyName(request.getCompanyName());
        workExperience.setCompanyLogoUrl(request.getCompanyLogoUrl());
        workExperience.setJobTitle(request.getJobTitle());
        workExperience.setEmploymentType(request.getEmploymentType());
        workExperience.setLocation(request.getLocation());
        workExperience.setStartDate(request.getStartDate());
        workExperience.setEndDate(request.getEndDate());
        workExperience.setIsCurrentJob(Boolean.TRUE.equals(request.getIsCurrentJob()));
        workExperience.setDescription(request.getDescription());
        if(request.getTechnologies()!=null) {
            workExperience.setTechnologies(request.getTechnologies());
        }
        if(request.getDisplayOrder()!=null) {
            workExperience.setDisplayOrder(request.getDisplayOrder());
        }

        WorkExperience updatedExp=workExperienceRepository.save(workExperience);

        return WorkExperienceMapper.toWorkExperience(updatedExp);
    }

    @Override
    public void deleteWorkExperience(Long resumeId, Long workExperienceId, Long candidateId) throws Exception {
        WorkExperience workExperience=getWorkExperienceEntity(workExperienceId);

        assertOwner(workExperience.getResume(),candidateId);

        workExperienceRepository.delete(workExperience);
    }

    @Override
    public WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception {
        return workExperienceRepository.findById(workExperienceId).orElseThrow(()->new Exception("Work Experience not found"));
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Resume not found");
        }
    }
}
