package com.sanskar.job.job_portal_resume_service.service;

import com.sanskar.job.dto.response.PersonalInfoResponse;
import com.sanskar.job.dto.response.ResumeResponse;
import com.sanskar.job.job_portal_resume_service.model.Resume;
import com.sanskar.job.job_portal_resume_service.payload.CreateResumeRequest;

import java.util.List;

public interface ResumeService {

    ResumeResponse createResume(Long candidateId, CreateResumeRequest request);

    ResumeResponse getResumeById(Long resumeId, Long candidateId) throws Exception;

    List<ResumeResponse> getMyResumes(Long candidateId);

    ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse request) throws Exception;

    ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary);

    ResumeResponse setDefaultResume(Long resumeId, Long candidateId);

    void deleteResume(Long resumeId, Long candidateId);

    Resume getResumeEntity(Long resumeId) throws Exception;
}
