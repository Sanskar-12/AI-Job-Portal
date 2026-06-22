package com.sanskar.job.job_portal_job_service.mapper;

import com.sanskar.job.dto.response.CompanyResponse;
import com.sanskar.job.dto.response.JobResponse;
import com.sanskar.job.job_portal_job_service.model.Job;
import com.sanskar.job.job_portal_job_service.model.embeddable.JobLocation;
import com.sanskar.job.job_portal_job_service.model.embeddable.SalaryRange;

public class JobMapper {

    public static JobResponse mapToResponse(Job job, CompanyResponse companyResponse) {
        JobLocation loc=job.getLocation();
        SalaryRange sal=job.getSalaryRange();

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .responsibilities(job.getResponsibilities())
                .benefits(job.getBenefits())
                .company(companyResponse)
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
