package com.sanskar.job.job_portal_job_service.mapper;

import com.sanskar.job.dto.response.*;
import com.sanskar.job.job_portal_job_service.model.Job;
import com.sanskar.job.job_portal_job_service.model.embeddable.JobLocation;
import com.sanskar.job.job_portal_job_service.model.embeddable.SalaryRange;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class JobMapper {

    public static JobResponse mapToResponse(Job job, CompanyResponse companyResponse) {
        JobLocation loc=job.getLocation();
        SalaryRange sal=job.getSalaryRange();

        Set<JobSkillResponse> skills=job.getSkills()==null ? Collections.emptySet() : job.getSkills().stream().map(JobSkillMapper::toJobSkillResponse).collect(Collectors.toSet());

        JobCategoryResponse category=JobCategoryMapper.toCategoryResponse(job.getCategory(),false);

        Set<JobTagResponse> tags=job.getTags()==null ? Collections.emptySet() : job.getTags().stream().map(JobTagMapper::toTagResponse).collect(Collectors.toSet());

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .company(companyResponse)
                .skills(skills)
                .category(category)
                .tags(tags)
                .address(loc!=null ? loc.getAddress() : null)
                .city(loc!=null ? loc.getCity() : null)
                .state(loc!=null ? loc.getState() : null)
                .country(loc!=null ? loc.getCountry() : null)
                .zipCode(loc!=null ? loc.getZipCode() : null)
                .minSalary(sal!=null ? sal.getMinSalary() : null)
                .maxSalary(sal!=null ? sal.getMaxSalary() : null)
                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .experienceLevel(job.getExperienceLevel())
                .status(job.getStatus())
                .openings(job.getOpenings())
                .applicationDeadline(job.getApplicationDeadline())
                .expiresAt(job.getExpiresAt())
                .active(job.getActive())
                .createdAt(job.getCreatedAt())
                .updatedAt(job.getUpdatedAt())
                .publishedAt(job.getPublishedAt())
                .closedAt(job.getClosedAt())
                .build();
    }
}
