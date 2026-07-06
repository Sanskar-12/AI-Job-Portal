package com.sanskar.job.job_portal_resume_service.service;

import com.sanskar.job.dto.response.EducationResponse;
import com.sanskar.job.job_portal_resume_service.payload.AddEducationRequest;

import java.util.List;

public interface EducationService {

    EducationResponse addEducation(Long resumeId, Long candidateId, AddEducationRequest request) throws Exception;

    List<EducationResponse> getEducations(Long resumeId);

    EducationResponse updateEducation(
            Long educationId,
            Long resumeId,
            Long candidateId,
            AddEducationRequest request
    ) throws Exception;

    void deleteEducation(Long educationId,Long resumeId,Long candidateId) throws Exception;
}
