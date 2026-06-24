package com.sanskar.job.job_portal_job_service.service.impl;

import com.sanskar.job.dto.response.JobSkillResponse;
import com.sanskar.job.job_portal_job_service.mapper.JobSkillMapper;
import com.sanskar.job.job_portal_job_service.model.JobSkill;
import com.sanskar.job.job_portal_job_service.payload.JobSkillRequest;
import com.sanskar.job.job_portal_job_service.repository.JobRepository;
import com.sanskar.job.job_portal_job_service.repository.JobSkillRepository;
import com.sanskar.job.job_portal_job_service.service.JobSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobSkillServiceImpl implements JobSkillService {

    private final JobSkillRepository jobSkillRepository;

    @Override
    public JobSkillResponse createSkill(JobSkillRequest request) throws Exception {
        if(jobSkillRepository.existsByName(request.getName())) {
            throw new Exception("Skill name already exist");
        }

        String slug=generateUniqueSlug(request.getName());

        JobSkill skill=JobSkill.builder()
                .name(request.getName())
                .slug(slug)
                .category(request.getCategory())
                .build();

        JobSkill savedSkill=jobSkillRepository.save(skill);

        return JobSkillMapper.toJobSkillResponse(savedSkill);
    }

    @Override
    public List<JobSkillResponse> getAllSkills() {
        return jobSkillRepository.findByActiveTrue().stream().map(JobSkillMapper::toJobSkillResponse).toList();
    }

    @Override
    public JobSkillResponse updateSkill(Long id, JobSkillRequest request) throws Exception {
        JobSkill skill=jobSkillRepository.findById(id).orElseThrow(()->new Exception("Job Skill not found"));

        if(!skill.getName().equals(request.getName()) && jobSkillRepository.existsByName(skill.getName())) {
            throw new Exception("Skill name already exist");
        }

        skill.setName(request.getName());
        skill.setCategory(request.getCategory());

        JobSkill updatedSkill=jobSkillRepository.save(skill);

        return JobSkillMapper.toJobSkillResponse(updatedSkill);
    }

    @Override
    public JobSkillResponse getSkillById(Long id) throws Exception {
        JobSkill skill=jobSkillRepository.findById(id).orElseThrow(()->new Exception("Job Skill not found"));

        return JobSkillMapper.toJobSkillResponse(skill);
    }

    @Override
    public void deleteSkill(Long id) throws Exception {
        JobSkill skill=jobSkillRepository.findById(id).orElseThrow(()->new Exception("Job Skill not found"));

        skill.setActive(false);

        jobSkillRepository.save(skill);
    }

    @Override
    public Set<JobSkill> getSkillsByIds(Set<Long> ids) {
        Set<JobSkill> skills=new HashSet<>(jobSkillRepository.findAllById(ids));

        return skills;
    }

    private String generateUniqueSlug(String name) {
        String base=name.toLowerCase().replaceAll("[^a-z0-9\\s-]]","")
                .trim().replaceAll("[\\s-]+","-");

        if(!jobSkillRepository.existsBySlug(base)) {
            return base;
        }

        int counter=1;
        while(jobSkillRepository.existsBySlug(base+"-"+counter)) {
            counter++;
        }

        return base+"-"+counter;
    }
}
