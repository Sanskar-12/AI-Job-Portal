package com.sanskar.job.job_portal_resume_service.mapper;

import com.sanskar.job.dto.response.WorkExperienceResponse;
import com.sanskar.job.job_portal_resume_service.model.WorkExperience;


public class WorkExperienceMapper {

    public static WorkExperienceResponse toWorkExperience(WorkExperience workExperience) {
        if(workExperience==null) return null;

        return WorkExperienceResponse.builder()
                .id(workExperience.getId())
                .companyName(workExperience.getCompanyName())
                .companyLogoUrl(workExperience.getCompanyLogoUrl())
                .jobTitle(workExperience.getJobTitle())
                .employmentType(workExperience.getEmploymentType())
                .location(workExperience.getLocation())
                .startDate(workExperience.getStartDate())
                .endDate(workExperience.getEndDate())
                .isCurrentJob(Boolean.TRUE.equals(workExperience.getIsCurrentJob()))
                .description(workExperience.getDescription())
                .technologies(workExperience.getTechnologies())
                .displayOrder(workExperience.getDisplayOrder())
                .build();
    }
}
