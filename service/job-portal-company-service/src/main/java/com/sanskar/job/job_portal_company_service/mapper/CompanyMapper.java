package com.sanskar.job.job_portal_company_service.mapper;

import com.sanskar.job.dto.response.CompanyResponse;
import com.sanskar.job.dto.response.SocialLinkResponse;
import com.sanskar.job.job_portal_company_service.model.Company;
import com.sanskar.job.job_portal_company_service.model.SocialLink;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyMapper {

    public static CompanyResponse mapToResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .slug(company.getSlug())
                .tagline(company.getTagline())
                .description(company.getDescription())
                .logoUrl(company.getLogoUrl())
                .coverImageUrl(company.getCoverImageUrl())
                .website(company.getWebsite())
                .email(company.getEmail())
                .phone(company.getPhone())
                .foundedYear(company.getFoundedYear())
                .companySize(company.getCompanySize())
                .companyType(company.getCompanyType())
                .industryType(company.getIndustryType())
                .companyStatus(company.getCompanyStatus())
                .verified(company.getVerified())
                .active(company.getActive())
                .ownerId(company.getOwnerId())
                .socialLink(mapSocialLinks(company.getSocialLink()))
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .verifiedAt(company.getVerifiedAt())
                .build();
    }

    private static List<SocialLinkResponse> mapSocialLinks(List<SocialLink> socialLinks) {
        if(socialLinks==null || socialLinks.isEmpty()) {
            return new ArrayList<SocialLinkResponse>();
        }
        return socialLinks.stream().map((s)->SocialLinkResponse.builder()
                .url(s.getUrl())
                .platform(s.getPlatform())
                .build()).collect(Collectors.toList());
    }
}
