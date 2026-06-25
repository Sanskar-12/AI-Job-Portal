package com.sanskar.job.job_portal_job_service.service;


import com.sanskar.job.dto.request.JobRequest;
import com.sanskar.job.dto.response.JobResponse;
import com.sanskar.job.job_portal_job_service.payload.JobSearchRequest;

import java.util.List;

public interface JobService {

    JobResponse createJob(Long employerId, JobRequest req) throws Exception;

    JobResponse getJobById(Long id) throws Exception;

    JobResponse updateJob(Long jobId, Long employerId, JobRequest req) throws Exception;

    List<JobResponse> getJobs(JobSearchRequest req);

    List<JobResponse> getJobsByCompany(Long companyId);

    JobResponse publishJob(Long jobId, Long employerId) throws Exception;

    JobResponse closeJob(Long jobId, Long employerId) throws Exception;

    void deleteJob(Long jobId, Long employerId) throws Exception;

    List<JobResponse> getAllJobsAdmin();
}
