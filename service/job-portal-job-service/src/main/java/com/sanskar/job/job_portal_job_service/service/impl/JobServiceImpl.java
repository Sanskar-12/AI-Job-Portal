package com.sanskar.job.job_portal_job_service.service.impl;

import com.sanskar.job.domain.JobStatus;
import com.sanskar.job.dto.request.JobRequest;
import com.sanskar.job.dto.response.CompanyResponse;
import com.sanskar.job.dto.response.JobResponse;
import com.sanskar.job.job_portal_job_service.mapper.JobMapper;
import com.sanskar.job.job_portal_job_service.model.Job;
import com.sanskar.job.job_portal_job_service.model.JobCategory;
import com.sanskar.job.job_portal_job_service.model.JobSkill;
import com.sanskar.job.job_portal_job_service.model.JobTag;
import com.sanskar.job.job_portal_job_service.model.embeddable.JobLocation;
import com.sanskar.job.job_portal_job_service.model.embeddable.SalaryRange;
import com.sanskar.job.job_portal_job_service.payload.JobSearchRequest;
import com.sanskar.job.job_portal_job_service.repository.JobRepository;
import com.sanskar.job.job_portal_job_service.repository.JobSpecification;
import com.sanskar.job.job_portal_job_service.service.JobCategoryService;
import com.sanskar.job.job_portal_job_service.service.JobService;
import com.sanskar.job.job_portal_job_service.service.JobSkillService;
import com.sanskar.job.job_portal_job_service.service.JobTagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobCategoryService jobCategoryService;
    private final JobSkillService jobSkillService;
    private final JobTagService jobTagService;

    @Override
    public JobResponse createJob(Long employerId, JobRequest req) throws Exception {

        JobCategory category=jobCategoryService.getCategoryEntityById(req.getCategoryId());
        Set<JobSkill> skills=req.getSkillIds()!=null ? jobSkillService.getSkillsByIds(req.getSkillIds()) : Collections.emptySet();
        Set<JobTag> tags=req.getTagIds()!=null ? jobTagService.getTagsById(req.getTagIds()) : Collections.emptySet();

        // fetch company by employer id
        Long companyId=1L;

        Job job=Job.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .requirements(req.getRequirements())
                .responsibilities(req.getResponsibilities())
                .category(category)
                .skills(skills)
                .tags(tags)
                .benefits(req.getBenefits())
                .companyId(companyId)
                .employerId(employerId)
                .location(buildLocation(req))
                .salaryRange(buildSalaryRange(req))
                .jobType(req.getJobType())
                .workMode(req.getWorkMode())
                .experienceLevel(req.getExperienceLevel())
                .openings(req.getOpenings()!=null ? req.getOpenings() : 1)
                .applicationDeadline(req.getApplicationDeadline())
                .expiresAt(req.getExpiresAt())
                .active(true)
                .status(JobStatus.DRAFT)
                .build();

        Job savedJob=jobRepository.save(job);

        return convertToResponse(savedJob);
    }



    @Override
    public JobResponse getJobById(Long id) throws Exception {
        Job job=jobRepository.findById(id).orElseThrow(()->new Exception("Job not found"));

        return convertToResponse(job);
    }

    @Override
    public JobResponse updateJob(Long jobId, Long employerId, JobRequest req) throws Exception {
        Job job=jobRepository.findById(jobId).orElseThrow(()->new Exception("Job not found"));

        assertEmployer(job,employerId);

        JobCategory category=jobCategoryService.getCategoryEntityById(req.getCategoryId());
        Set<JobSkill> skills=req.getSkillIds()!=null ? jobSkillService.getSkillsByIds(req.getSkillIds()) : Collections.emptySet();
        Set<JobTag> tags=req.getTagIds()!=null ? jobTagService.getTagsById(req.getTagIds()) : Collections.emptySet();

        job.setTitle(req.getTitle());
        job.setDescription(req.getDescription());
        job.setRequirements(req.getRequirements());
        job.setResponsibilities(req.getResponsibilities());
        job.setBenefits(req.getBenefits());
        job.setCategory(category);
        job.setSkills(skills);
        job.setTags(tags);
        job.setLocation(buildLocation(req));
        job.setSalaryRange(buildSalaryRange(req));
        job.setJobType(req.getJobType());
        job.setWorkMode(req.getWorkMode());
        job.setExperienceLevel(req.getExperienceLevel());
        job.setOpenings(req.getOpenings()!=null ? req.getOpenings() : 1);
        job.setApplicationDeadline(req.getApplicationDeadline());
        job.setExpiresAt(req.getExpiresAt());

        Job savedJob=jobRepository.save(job);

        return convertToResponse(savedJob);
    }

    @Override
    @Transactional()
    public List<JobResponse> getJobs(JobSearchRequest req) {
        List<Job> jobs=jobRepository.findAll(JobSpecification.build(req));

        return jobs.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobsByCompany(Long companyId) {
        List<Job> jobs=jobRepository.findByCompanyId(companyId);

        return jobs.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional()
    public JobResponse publishJob(Long jobId, Long employerId) throws Exception {
        Job job=jobRepository.findById(jobId).orElseThrow(()->new Exception("Job not found"));

        // check whether the job is published by same employer
        assertEmployer(job,employerId);

        if(job.getStatus()==JobStatus.CLOSED || job.getStatus()==JobStatus.EXPIRED) {
            throw new Exception("Job is Expired");
        }

        job.setStatus(JobStatus.OPEN);
        job.setPublishedAt(LocalDateTime.now());
        job.setActive(true);

        return convertToResponse(jobRepository.save(job));
    }



    @Override
    @Transactional()
    public JobResponse closeJob(Long jobId, Long employerId) throws Exception {
        Job job=jobRepository.findById(jobId).orElseThrow(()->new Exception("Job not found"));

        // check whether the job is published by same employer
        assertEmployer(job,employerId);

        job.setStatus(JobStatus.CLOSED);
        job.setClosedAt(LocalDateTime.now());
        job.setActive(false);

        return convertToResponse(jobRepository.save(job));
    }

    @Override
    public void deleteJob(Long jobId, Long employerId) throws Exception {
        Job job=jobRepository.findById(jobId).orElseThrow(()->new Exception("Job not found"));

        // check whether the job is published by same employer
        assertEmployer(job,employerId);

        jobRepository.delete(job);
    }

    @Override
    @Transactional()
    public List<JobResponse> getAllJobsAdmin() {
        List<Job> jobs=jobRepository.findAll();

        return jobs.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    private JobResponse convertToResponse(Job savedJob) {
        // fetch company response
        CompanyResponse companyResponse=CompanyResponse.builder()
                .id(savedJob.getCompanyId())
                .build();
        return JobMapper.mapToResponse(savedJob,companyResponse);
    }

    private SalaryRange buildSalaryRange(JobRequest req) {
        return SalaryRange.builder()
                .minSalary(req.getMinSalary())
                .maxSalary(req.getMaxSalary())
                .build();
    }

    private JobLocation buildLocation(JobRequest req) {
        return JobLocation.builder()
                .address(req.getAddress())
                .city(req.getCity())
                .state(req.getState())
                .country(req.getState())
                .zipCode(req.getZipCode())
                .build();
    }

    private void assertEmployer(Job job, Long employerId) throws Exception {
        if(!job.getEmployerId().equals(employerId)) {
            throw new Exception("You are not the employer who posted this job");
        }
    }
}
