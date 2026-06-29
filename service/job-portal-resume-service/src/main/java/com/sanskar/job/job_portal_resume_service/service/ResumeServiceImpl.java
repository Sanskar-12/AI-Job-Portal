package com.sanskar.job.job_portal_resume_service.service;

import com.sanskar.job.dto.response.PersonalInfoResponse;
import com.sanskar.job.dto.response.ResumeResponse;
import com.sanskar.job.job_portal_resume_service.mapper.ResumeMapper;
import com.sanskar.job.job_portal_resume_service.model.PersonalInfo;
import com.sanskar.job.job_portal_resume_service.model.Resume;
import com.sanskar.job.job_portal_resume_service.payload.CreateResumeRequest;
import com.sanskar.job.job_portal_resume_service.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Override
    public ResumeResponse createResume(Long candidateId, CreateResumeRequest request) {

        if(Boolean.TRUE.equals(request.getIsDefault())) {
            resumeRepository.findByCandidateIdAndIsDefaultTrue(candidateId).ifPresent((existingResume)->{
                existingResume.setIsDefault(false);
                resumeRepository.save(existingResume);
            });
        }

        Resume resume=Resume.builder()
                .candidateId(candidateId)
                .title(request.getTitle())
                .template(request.getTemplate())
                .visibility(request.getVisibility())
                .isDefault(Boolean.TRUE.equals(request.getIsDefault()))
                .build();

        Resume savedResume=resumeRepository.save(resume);

        return ResumeMapper.toResponse(savedResume);
    }

    @Override
    public ResumeResponse getResumeById(Long resumeId, Long candidateId) throws Exception {
        Resume resume=getResumeEntity(resumeId);
        assertOwner(resume,candidateId);
        return buildFullResponse(resume);
    }

    @Override
    public List<ResumeResponse> getMyResumes(Long candidateId) {
        return resumeRepository.findByCandidateId(candidateId).stream().map(this::buildFullResponse).toList();
    }

    @Override
    public ResumeResponse updatePersonalInfo(Long resumeId, Long candidateId, PersonalInfoResponse request) throws Exception {
        Resume resume=getResumeEntity(resumeId);
        assertOwner(resume,candidateId);

        PersonalInfo personalInfo=resume.getPersonalInfo();

        if(personalInfo==null) {
            personalInfo=new PersonalInfo();
        }

        if(request.getFirstName()!=null) {
            personalInfo.setFirstName(request.getFirstName());
        }
        if(request.getLastName()!=null) {
            personalInfo.setLastName(request.getLastName());
        }
        if(request.getHeadline()!=null) {
            personalInfo.setHeadline(request.getHeadline());
        }
        if(request.getEmail()!=null) {
            personalInfo.setEmail(request.getEmail());
        }
        if(request.getPhone()!=null) {
            personalInfo.setPhone(request.getPhone());
        }
        if(request.getCity()!=null) {
            personalInfo.setCity(request.getCity());
        }
        if(request.getCountry()!=null) {
            personalInfo.setCountry(request.getCountry());
        }
        if(request.getLinkedinUrl()!=null) {
            personalInfo.setLinkedinUrl(request.getLinkedinUrl());
        }
        if(request.getGithubUrl()!=null) {
            personalInfo.setGithubUrl(request.getGithubUrl());
        }
        if(request.getPortfolioUrl()!=null) {
            personalInfo.setPortfolioUrl(request.getPortfolioUrl());
        }
        if(request.getWebsiteUrl()!=null) {
            personalInfo.setWebsiteUrl(request.getWebsiteUrl());
        }

        resume.setPersonalInfo(personalInfo);

        Resume updatedResume=resumeRepository.save(resume);

        return buildFullResponse(updatedResume);
    }

    @Override
    public ResumeResponse updateSummary(Long resumeId, Long candidateId, String summary) {
        return null;
    }

    @Override
    public ResumeResponse setDefaultResume(Long resumeId, Long candidateId) {
        return null;
    }

    @Override
    public void deleteResume(Long resumeId, Long candidateId) {

    }

    @Override
    public Resume getResumeEntity(Long resumeId) throws Exception {
        return resumeRepository.findById(resumeId).orElseThrow(()->new Exception("Resume not found "+resumeId));
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Resume not found with id");
        }
    }

    private ResumeResponse buildFullResponse(Resume req
    ) {
        return ResumeMapper.toResponse(req);
    }
}
