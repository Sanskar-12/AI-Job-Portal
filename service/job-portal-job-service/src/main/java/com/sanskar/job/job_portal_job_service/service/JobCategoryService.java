package com.sanskar.job.job_portal_job_service.service;


import com.sanskar.job.dto.response.JobCategoryResponse;
import com.sanskar.job.job_portal_job_service.model.JobCategory;
import com.sanskar.job.job_portal_job_service.payload.JobCategoryRequest;

import java.util.List;

public interface JobCategoryService {

    JobCategoryResponse createCategory(JobCategoryRequest req) throws Exception;

    List<JobCategoryResponse> getAllCategories();

    JobCategoryResponse updateCategory(Long id, JobCategoryRequest req) throws Exception;

    JobCategoryResponse getCategoryById(Long id) throws Exception;

    void deleteCategory(Long id) throws Exception;

    JobCategory getCategoryEntityById(Long id) throws Exception;
}
