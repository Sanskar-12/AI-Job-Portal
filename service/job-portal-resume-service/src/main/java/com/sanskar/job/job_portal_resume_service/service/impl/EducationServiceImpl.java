package com.sanskar.job.job_portal_resume_service.service.impl;

import com.sanskar.job.dto.response.EducationResponse;
import com.sanskar.job.job_portal_resume_service.mapper.ResumeMapper;
import com.sanskar.job.job_portal_resume_service.model.Education;
import com.sanskar.job.job_portal_resume_service.model.Resume;
import com.sanskar.job.job_portal_resume_service.payload.AddEducationRequest;
import com.sanskar.job.job_portal_resume_service.repository.EducationRepository;
import com.sanskar.job.job_portal_resume_service.service.EducationService;
import com.sanskar.job.job_portal_resume_service.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepository educationRepository;
    private final ResumeService resumeService;

    @Override
    public EducationResponse addEducation(Long resumeId, Long candidateId, AddEducationRequest request) throws Exception {
        Resume resume=resumeService.getResumeEntity(resumeId);
        assertOwner(resume,candidateId);

        Education education=Education.builder()
                .resume(resume)
                .institutionName(request.getInstitutionName())
                .degree(request.getDegree())
                .fieldOfStudy(request.getFieldOfStudy())
                .grade(request.getGrade())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isCurrentlyStudying(Boolean.TRUE.equals(request.getIsCurrentlyStudying()))
                .description(request.getDescription())
                .displayOrder(request.getDisplayOrder()!=null ? request.getDisplayOrder() : 0)
                .build();

        Education savedEducation=educationRepository.save(education);

        return ResumeMapper.toEducationResponse(savedEducation);
    }

    @Override
    public List<EducationResponse> getEducations(Long resumeId) {
        return educationRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId).stream().map(ResumeMapper::toEducationResponse).toList();
    }

    @Override
    public EducationResponse updateEducation(Long educationId, Long resumeId, Long candidateId, AddEducationRequest request) throws Exception {
        Education education=educationRepository.findById(educationId).orElseThrow(()->new Exception("Education does not exist"));
        assertOwner(education.getResume(),candidateId);

        education.setInstitutionName(request.getInstitutionName());
        education.setDegree(request.getDegree());
        education.setFieldOfStudy(request.getFieldOfStudy());
        education.setGrade(request.getGrade());
        education.setStartDate(request.getStartDate());
        education.setEndDate(request.getEndDate());
        education.setIsCurrentlyStudying(Boolean.TRUE.equals(request.getIsCurrentlyStudying()));
        education.setDescription(request.getDescription());
        if(request.getDisplayOrder()!=null) {
            education.setDisplayOrder(request.getDisplayOrder());
        }

        Education savedEducation=educationRepository.save(education);

        return ResumeMapper.toEducationResponse(savedEducation);
    }

    @Override
    public void deleteEducation(Long educationId, Long resumeId, Long candidateId) throws Exception {
        Education education=educationRepository.findById(educationId).orElseThrow(()->new Exception("Education does not exist"));
        assertOwner(education.getResume(),candidateId);

        educationRepository.delete(education);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Resume not found");
        }
    }
}
