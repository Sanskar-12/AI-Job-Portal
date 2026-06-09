package com.sanskar.job.dto.response;

import com.sanskar.job.domain.CompanySize;
import com.sanskar.job.domain.CompanyStatus;
import com.sanskar.job.domain.CompanyType;
import com.sanskar.job.domain.IndustryType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompanyResponse {

    private Long id;

    private String name;

    private String slug;

    private String tagline;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    private String website;

    private String email;

    private String phone;

    private Integer foundedYear;

    private CompanySize companySize;

    private CompanyType companyType;

    private IndustryType industryType;

    private CompanyStatus companyStatus;

    private Boolean verified;

    private Boolean active;

    private Long ownerId;

    private List<SocialLinkResponse> socialLink;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime verifiedAt;
}
