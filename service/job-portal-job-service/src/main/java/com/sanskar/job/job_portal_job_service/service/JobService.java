package com.sanskar.job.job_portal_job_service.service;


import com.sanskar.job.dto.request.JobRequest;
import com.sanskar.job.dto.response.JobResponse;
import com.sanskar.job.job_portal_job_service.payload.JobSearchRequest;

import java.util.List;

public interface JobService {

    JobResponse createJob(Long employerId, JobRequest req);

    JobResponse getJobById(Long id);

    JobResponse updateJob(Long jobId, Long employerId, JobRequest req);

    List<JobResponse> getJobs(JobSearchRequest req);

    List<JobResponse> getJobsByCompany(Long companyId);

    JobResponse publishJob(Long jobId, Long employerId);

    JobResponse closeJob(Long jobId, Long employerId);

    JobResponse deleteJob(Long jobId, Long employerId);

    List<JobResponse> getAllJobsAdmin();
}
