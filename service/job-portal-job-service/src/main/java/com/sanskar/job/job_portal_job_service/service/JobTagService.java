package com.sanskar.job.job_portal_job_service.service;


import com.sanskar.job.dto.response.JobTagResponse;
import com.sanskar.job.job_portal_job_service.mapper.JobTagRequest;
import com.sanskar.job.job_portal_job_service.model.JobTag;

import java.util.List;

public interface JobTagService {

    JobTagResponse createTag(JobTagRequest request) throws Exception;

    List<JobTagResponse> getAllTags();

    JobTagResponse updateTag(Long id, JobTagRequest request) throws Exception;

    JobTagResponse getTagById(Long id) throws Exception;

    void deleteTag(Long id) throws Exception;

    JobTag getTagEntityById(Long id) throws Exception;
}
