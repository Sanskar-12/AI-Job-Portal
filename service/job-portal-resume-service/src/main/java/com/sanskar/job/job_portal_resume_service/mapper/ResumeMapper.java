package com.sanskar.job.job_portal_resume_service.mapper;

import com.sanskar.job.dto.response.*;
import com.sanskar.job.job_portal_resume_service.model.*;

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


    public static EducationResponse toEducationResponse(Education edu) {
        if(edu==null) return null;
        return EducationResponse.builder()
                .id(edu.getId())
                .institutionName(edu.getInstitutionName())
                .degree(edu.getDegree())
                .fieldOfStudy(edu.getFieldOfStudy())
                .grade(edu.getGrade())
                .startDate(edu.getStartDate())
                .endDate(edu.getEndDate())
                .isCurrentlyStudying(edu.getIsCurrentlyStudying())
                .description(edu.getDescription())
                .displayOrder(edu.getDisplayOrder())
                .build();
    }

    public static ProjectResponse toProjectResponse(Project project) {
        if(project==null) return null;

        return ProjectResponse.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .technologies(project.getTechnologies())
                .projectUrl(project.getProjectUrl())
                .sourceCodeUrl(project.getSourceCodeUrl())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .isOngoing(project.getIsOngoing())
                .displayOrder(project.getDisplayOrder())
                .build();
    }
}
