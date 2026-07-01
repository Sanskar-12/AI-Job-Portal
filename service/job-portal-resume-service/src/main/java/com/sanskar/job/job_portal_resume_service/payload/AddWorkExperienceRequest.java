package com.sanskar.job.job_portal_resume_service.payload;

import com.sanskar.job.domain.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddWorkExperienceRequest {

    @NotBlank(message = "Company Name is required")
    private String companyName;

    private String companyLogoUrl;

    @NotBlank(message = "Job Title is required")
    private String jobTitle;

    private JobType employmentType;

    private String location;

    @NotNull(message = "Start Date is required")
    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isCurrentJob=false;

    private String description;

    private List<String> technologies;

    private Integer displayOrder;
}
