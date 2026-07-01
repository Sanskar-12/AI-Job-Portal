package com.sanskar.job.job_portal_resume_service.service;


import com.sanskar.job.dto.response.WorkExperienceResponse;
import com.sanskar.job.job_portal_resume_service.model.WorkExperience;
import com.sanskar.job.job_portal_resume_service.payload.AddWorkExperienceRequest;

import java.util.List;

public interface WorkExperienceService {

    WorkExperienceResponse addWorkExperience(Long resumeId, Long candidateId, AddWorkExperienceRequest request) throws Exception;

    List<WorkExperienceResponse> getWorkExperiences(Long resumeId);

    WorkExperienceResponse updateWorkExperience(Long resumeId,Long workExperienceId, AddWorkExperienceRequest request, Long candidateId) throws Exception;

    void deleteWorkExperience(Long resumeId,Long workExperienceId, Long candidateId) throws Exception;

    WorkExperience getWorkExperienceEntity(Long workExperienceId) throws Exception;
}
