package com.sanskar.job.job_portal_resume_service.service.impl;

import com.sanskar.job.dto.response.ResumeSkillResponse;
import com.sanskar.job.job_portal_resume_service.mapper.ResumeMapper;
import com.sanskar.job.job_portal_resume_service.model.Resume;
import com.sanskar.job.job_portal_resume_service.model.ResumeSkill;
import com.sanskar.job.job_portal_resume_service.payload.AddResumeSkillRequest;
import com.sanskar.job.job_portal_resume_service.repository.ResumeSkillRepository;
import com.sanskar.job.job_portal_resume_service.service.ResumeService;
import com.sanskar.job.job_portal_resume_service.service.ResumeSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeSkillServiceImpl implements ResumeSkillService {

    private final ResumeSkillRepository resumeSkillRepository;
    private final ResumeService resumeService;

    @Override
    public ResumeSkillResponse addSkill(Long resumeId, Long candidateId, AddResumeSkillRequest request) throws Exception {
        Resume resume=resumeService.getResumeEntity(resumeId);
        assertOwner(resume,candidateId);

        ResumeSkill skill=ResumeSkill.builder()
                .resume(resume)
                .skillName(request.getSkillName())
                .proficiencyLevel(request.getProficiencyLevel())
                .yearsOfExperience(request.getYearsOfExperience())
                .displayOrder(request.getDisplayOrder()!=null ? request.getDisplayOrder() : 0)
                .build();

        ResumeSkill savedSkill=resumeSkillRepository.save(skill);

        return ResumeMapper.toSkillResponse(savedSkill);
    }

    @Override
    public List<ResumeSkillResponse> getSkills(Long resumeId) {
        return resumeSkillRepository.findByResume_IdOrderByDisplayOrderAsc(resumeId).stream().map(ResumeMapper::toSkillResponse).toList();
    }

    @Override
    public ResumeSkillResponse updateSkill(Long skillId, Long resumeId, Long candidateId, AddResumeSkillRequest request) throws Exception {
        ResumeSkill skill=getSkillEntity(skillId);
        assertOwner(skill.getResume(),candidateId);

        skill.setSkillName(request.getSkillName());
        skill.setProficiencyLevel(request.getProficiencyLevel());
        skill.setYearsOfExperience(request.getYearsOfExperience());
        if(request.getDisplayOrder()!=null) {
            skill.setDisplayOrder(request.getDisplayOrder());
        }

        return ResumeMapper.toSkillResponse(resumeSkillRepository.save(skill));
    }


    @Override
    public void deleteSkill(Long skillId, Long resumeId, Long candidateId) throws Exception {
        ResumeSkill skill=getSkillEntity(skillId);
        assertOwner(skill.getResume(),candidateId);

        resumeSkillRepository.delete(skill);
    }

    private void assertOwner(Resume resume, Long candidateId) throws Exception {
        if(!resume.getCandidateId().equals(candidateId)) {
            throw new Exception("Resume not found");
        }
    }

    private ResumeSkill getSkillEntity(Long skillId) throws Exception {
        return resumeSkillRepository.findById(skillId).orElseThrow(()->new Exception("Skill not found"));
    }
}
