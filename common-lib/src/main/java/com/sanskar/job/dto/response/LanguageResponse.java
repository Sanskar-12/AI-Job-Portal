package com.sanskar.job.dto.response;

import com.sanskar.job.domain.LanguageProficiency;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageResponse {
    private Long id;
    private String languageName;
    private LanguageProficiency proficiency;
    private Integer displayOrder;
}
