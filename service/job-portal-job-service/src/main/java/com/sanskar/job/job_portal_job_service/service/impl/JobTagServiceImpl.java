package com.sanskar.job.job_portal_job_service.service.impl;

import com.sanskar.job.dto.response.JobTagResponse;
import com.sanskar.job.job_portal_job_service.mapper.JobTagMapper;
import com.sanskar.job.job_portal_job_service.mapper.JobTagRequest;
import com.sanskar.job.job_portal_job_service.model.JobTag;
import com.sanskar.job.job_portal_job_service.repository.JobTagRepository;
import com.sanskar.job.job_portal_job_service.service.JobTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JobTagServiceImpl implements JobTagService {

    private final JobTagRepository jobTagRepository;

    @Override
    public JobTagResponse createTag(JobTagRequest request) throws Exception {

        if(jobTagRepository.existsByName(request.getName())) {
            throw new Exception("Tag name already exist");
        }

        String slug=generateUniqueSlug(request.getName());

        JobTag jobTag=JobTag.builder()
                .name(request.getName())
                .slug(slug)
                .build();

        JobTag savedJobTag=jobTagRepository.save(jobTag);

        return JobTagMapper.toTagResponse(savedJobTag);
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        return jobTagRepository.findAll().stream().map(JobTagMapper::toTagResponse).toList();
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest request) throws Exception {
        JobTag jobTag=getTagEntityById(id);

        if(!jobTag.getName().equals(request.getName()) && jobTagRepository.existsByName(request.getName())) {
            throw new Exception("Tag name already exist");
        }

        jobTag.setName(request.getName());

        return JobTagMapper.toTagResponse(jobTagRepository.save(jobTag));
    }

    @Override
    public JobTagResponse getTagById(Long id) throws Exception {
        JobTag jobTag=getTagEntityById(id);

        return JobTagMapper.toTagResponse(jobTag);
    }

    @Override
    public void deleteTag(Long id) throws Exception {
        JobTag jobTag=getTagEntityById(id);

        jobTagRepository.delete(jobTag);
    }

    @Override
    public JobTag getTagEntityById(Long id) throws Exception {
        return jobTagRepository.findById(id).orElseThrow(()->new Exception("Job Tag not found"));
    }

    @Override
    public Set<JobTag> getTagsById(Set<Long> ids) {
        Set<JobTag> tags= new HashSet<>(jobTagRepository.findAllById(ids));
        return tags;
    }

    private String generateUniqueSlug(String name) {
        String base=name.toLowerCase().replaceAll("[^a-z0-9\\s-]]","")
                .trim().replaceAll("[\\s-]+","-");

        if(!jobTagRepository.existsBySlug(base)) {
            return base;
        }

        int counter=1;
        while(jobTagRepository.existsBySlug(base+"-"+counter)) {
            counter++;
        }

        return base+"-"+counter;
    }
}
