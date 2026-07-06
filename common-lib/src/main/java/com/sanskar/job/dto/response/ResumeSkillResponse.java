package com.sanskar.job.dto.response;

import com.sanskar.job.domain.ProficiencyLevel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeSkillResponse {

    private Long id;
    private String skillName;
    private ProficiencyLevel proficiencyLevel;
    private Integer yearsOfExperience;
    private Integer displayOrder;
}
