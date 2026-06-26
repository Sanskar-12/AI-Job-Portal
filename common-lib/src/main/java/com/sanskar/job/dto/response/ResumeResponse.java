package com.sanskar.job.dto.response;

import com.sanskar.job.domain.ResumeTemplate;
import com.sanskar.job.domain.ResumeVisibility;
import com.sanskar.job.dto.PersonalInfo;

import java.time.LocalDateTime;

public class ResumeResponse {

    private Long id;
    private Long candidateId;
    private String title;
    private ResumeTemplate template;
    private ResumeVisibility visibility;
    private Boolean isDefault;
    private PersonalInfo personalInfo;
    private String summary;
    private Integer completionScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
