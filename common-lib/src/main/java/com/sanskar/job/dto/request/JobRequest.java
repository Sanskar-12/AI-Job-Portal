package com.sanskar.job.dto.request;

import com.sanskar.job.domain.ExperienceLevel;
import com.sanskar.job.domain.JobType;
import com.sanskar.job.domain.WorkMode;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {

    @NotBlank(message = "Job Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private String requirements;
    private String responsibilities;
    private String benefits;

    @NotNull(message = "Category is required")
    private Long categoryId;

    private Set<Long> skillIds;

    private Set<Long> tagIds;

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @DecimalMin(value="0.0", inclusive = true, message = "Min salary must not be negative")
    private BigDecimal minSalary;

    @DecimalMin(value="0.0", inclusive = true, message = "Min salary must not be negative")
    private BigDecimal maxSalary;
    private JobType jobType;
    private WorkMode workMode;
    private ExperienceLevel experienceLevel;

    @Min(value=1, message = "Openings must be atleast 1")
    private Integer openings=1;

    private LocalDate applicationDeadline;
    private LocalDate expiresAt;
}
