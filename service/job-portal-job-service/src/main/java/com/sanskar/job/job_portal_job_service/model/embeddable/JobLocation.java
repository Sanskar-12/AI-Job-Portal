package com.sanskar.job.job_portal_job_service.model.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class JobLocation {

    private String address;
    private String city;
    private String country;
    private String state;
    private String zipCode;
}
