package com.sanskar.job.job_portal_job_service.mapper;

import com.sanskar.job.dto.response.JobSkillResponse;
import com.sanskar.job.job_portal_job_service.model.JobSkill;

public class JobSkillMapper {

    public static JobSkillResponse toJobSkillResponse(JobSkill jobSkill) {
        return JobSkillResponse.builder()
                .id(jobSkill.getId())
                .name(jobSkill.getName())
                .slug(jobSkill.getSlug())
                .category(jobSkill.getCategory())
                .active(jobSkill.getActive())
                .build();
    }
}
