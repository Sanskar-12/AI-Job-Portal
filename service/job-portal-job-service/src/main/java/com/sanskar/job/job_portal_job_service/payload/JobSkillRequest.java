package com.sanskar.job.job_portal_job_service.payload;

import com.sanskar.job.domain.SkillCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSkillRequest {

    @NotBlank(message = "Skill name is required")
    @Size(max = 100,message = "Name must not exceed 100 characters")
    private String name;

    @NotNull(message = "Skill Category is required")
    private SkillCategory category;
}
