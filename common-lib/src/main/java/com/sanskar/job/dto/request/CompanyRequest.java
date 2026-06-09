package com.sanskar.job.dto.request;

import com.sanskar.job.domain.CompanySize;
import com.sanskar.job.domain.CompanyType;
import com.sanskar.job.domain.IndustryType;
import com.sanskar.job.dto.response.SocialLinkResponse;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyRequest {

    @NotBlank(message = "Company name is required")
    private String name;

    private String tagline;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    @Pattern(regexp = "^(https?://).*",message = "Website must be a valid URL")
    private String website;

    @Email(message = "Company email must be valid")
    private String email;

    private String phone;

    @Min(value = 1800, message = "Found Year seems too old")
    @Max(value = 2100, message = "Founded Year is invalid")
    private Integer foundedYear;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotNull(message = "Company type is required")
    private CompanyType companyType;

    @NotNull(message = "Industry Type is required")
    private IndustryType industryType;

    private String registrationNumber;

    private List<SocialLinkResponse> socialLinks;
}
