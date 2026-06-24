package com.sanskar.job.job_portal_job_service.service;

import com.sanskar.job.dto.response.JobSkillResponse;
import com.sanskar.job.job_portal_job_service.model.JobSkill;
import com.sanskar.job.job_portal_job_service.payload.JobSkillRequest;

import java.util.List;
import java.util.Set;

public interface JobSkillService {

    JobSkillResponse createSkill(JobSkillRequest request) throws Exception;

    List<JobSkillResponse> getAllSkills();

    JobSkillResponse updateSkill(Long id,JobSkillRequest request) throws Exception;

    JobSkillResponse getSkillById(Long id) throws Exception;

    void deleteSkill(Long id) throws Exception;

    Set<JobSkill> getSkillsByIds(Set<Long> ids);
}
