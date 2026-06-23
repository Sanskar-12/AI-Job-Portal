package com.sanskar.job.job_portal_job_service.service.impl;

import com.sanskar.job.dto.response.JobCategoryResponse;
import com.sanskar.job.job_portal_job_service.mapper.JobCategoryMapper;
import com.sanskar.job.job_portal_job_service.model.JobCategory;
import com.sanskar.job.job_portal_job_service.payload.JobCategoryRequest;
import com.sanskar.job.job_portal_job_service.repository.JobCategoryRepository;
import com.sanskar.job.job_portal_job_service.service.JobCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCategoryServiceImpl implements JobCategoryService {

    private final JobCategoryRepository jobCategoryRepository;

    @Override
    public JobCategoryResponse createCategory(JobCategoryRequest req) throws Exception {
        if(jobCategoryRepository.existsByName(req.getName())) {
            throw new Exception("Category name already exists");
        }

        JobCategory parent=null;

        if(req.getParentId()!=null) {
            parent=getCategoryEntityById(req.getParentId());
        }

        String slug=generateUniqueSlug(req.getName());

        JobCategory category=JobCategory.builder()
                .name(req.getName())
                .slug(slug)
                .description(req.getDescription())
                .iconUrl(req.getIconUrl())
                .parentCategory(parent)
                .build();

        JobCategory savedJobCategory=jobCategoryRepository.save(category);

        return JobCategoryMapper.toCategoryResponse(savedJobCategory,true);

    }

    @Override
    public List<JobCategoryResponse> getAllCategories() {
        return jobCategoryRepository.findByActiveTrue().stream().map((c)->JobCategoryMapper.toCategoryResponse(c,false)).toList();
    }

    @Override
    public JobCategoryResponse updateCategory(Long id, JobCategoryRequest req) throws Exception {
        JobCategory jobCategory=getCategoryEntityById(id);

        if(jobCategory.getName().equals(req.getName()) && jobCategoryRepository.existsByName(req.getName())) {
            throw new Exception("Category name already exists");
        }

        JobCategory parent=null;

        if(req.getParentId()!=null) {
            if(req.getParentId().equals(id)) {
                throw new Exception("A Category cannot be its own parent");
            }
            parent=getCategoryEntityById(req.getParentId());
        }

        jobCategory.setName(req.getName());
        jobCategory.setDescription(req.getDescription());
        jobCategory.setIconUrl(req.getIconUrl());
        jobCategory.setParentCategory(parent);

        JobCategory updatedJobCategory=jobCategoryRepository.save(jobCategory);

        return JobCategoryMapper.toCategoryResponse(updatedJobCategory,true);

    }

    @Override
    public JobCategoryResponse getCategoryById(Long id) throws Exception {
        JobCategory jobCategory=getCategoryEntityById(id);
        return JobCategoryMapper.toCategoryResponse(jobCategory,true);
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        JobCategory jobCategory=getCategoryEntityById(id);

        jobCategory.setActive(false);

        jobCategoryRepository.save(jobCategory);
    }

    @Override
    public JobCategory getCategoryEntityById(Long id) throws Exception {
        return jobCategoryRepository.findById(id).orElseThrow(()->new Exception("Category not found"));
    }

    private String generateUniqueSlug(String name) {
        String base=name.toLowerCase().replaceAll("[^a-z0-9\\s-]]","")
                .trim().replaceAll("[\\s-]+","-");

        if(!jobCategoryRepository.existsBySlug(base)) {
            return base;
        }

        int counter=1;
        while(jobCategoryRepository.existsBySlug(base+"-"+counter)) {
            counter++;
        }

        return base+"-"+counter;
    }
}
