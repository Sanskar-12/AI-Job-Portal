package com.sanskar.job.job_portal_job_service.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class SalaryRange {

    private BigDecimal minSalary;
    private BigDecimal maxSalary;
}
