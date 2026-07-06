package com.sanskar.job.job_portal_resume_service.mapper;

import com.sanskar.job.dto.response.PersonalInfoResponse;
import com.sanskar.job.dto.response.ResumeResponse;
import com.sanskar.job.dto.response.ResumeSkillResponse;
import com.sanskar.job.dto.response.SocialLinkResponse;
import com.sanskar.job.job_portal_resume_service.model.PersonalInfo;
import com.sanskar.job.job_portal_resume_service.model.Resume;
import com.sanskar.job.job_portal_resume_service.model.ResumeSkill;

public class ResumeMapper {
    public static PersonalInfoResponse toPersonalInfoResponse(PersonalInfo personalInfo) {
        return PersonalInfoResponse.builder()
                .firstName(personalInfo.getFirstName())
                .lastName(personalInfo.getLastName())
                .email(personalInfo.getEmail())
                .phone(personalInfo.getPhone())
                .headline(personalInfo.getHeadline())
                .country(personalInfo.getCountry())
                .city(personalInfo.getCity())
                .githubUrl(personalInfo.getGithubUrl())
                .linkedinUrl(personalInfo.getLinkedinUrl())
                .portfolioUrl(personalInfo.getPortfolioUrl())
                .websiteUrl(personalInfo.getWebsiteUrl())
                .build();
    }

    public static ResumeResponse toResponse(Resume request) {

        if(request==null) return null;

        ResumeResponse resumeResponse=ResumeResponse.builder()
                .id(request.getId())
                .candidateId(request.getCandidateId())
                .title(request.getTitle())
                .template(request.getTemplate())
                .visibility(request.getVisibility())
                .isDefault(request.getIsDefault())
                .personalInfo(toPersonalInfoResponse(request.getPersonalInfo()))
                .summary(request.getSummary())
                .completionScore(request.getCompletionScore())
                .createdAt(request.getCreatedAt())
                .updatedAt(request.getUpdatedAt())
                .build();

        return resumeResponse;
    }

    public static ResumeSkillResponse toSkillResponse(ResumeSkill skill) {
        if(skill==null) return null;

        return ResumeSkillResponse.builder()
                .id(skill.getId())
                .skillName(skill.getSkillName())
                .proficiencyLevel(skill.getProficiencyLevel())
                .yearsOfExperience(skill.getYearsOfExperience())
                .displayOrder(skill.getDisplayOrder())
                .build();
    }
}
