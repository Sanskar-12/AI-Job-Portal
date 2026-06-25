package com.sanskar.job.job_portal_job_service.mapper;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JobTagRequest {

    @NotBlank(message = "Tag Name is required")
    private String name;

}
