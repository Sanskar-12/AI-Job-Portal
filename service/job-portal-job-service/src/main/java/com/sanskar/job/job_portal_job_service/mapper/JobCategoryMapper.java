package com.sanskar.job.job_portal_job_service.mapper;

import com.sanskar.job.dto.response.JobCategoryResponse;
import com.sanskar.job.job_portal_job_service.model.JobCategory;

import java.util.List;

public class JobCategoryMapper {

    public static JobCategoryResponse toCategoryResponse(JobCategory request,boolean includeChildren) {

        List<JobCategoryResponse> subCategories = (includeChildren && request.getSubCategories()!=null)
                ? request.getSubCategories()
                .stream()
                .map(subCategory -> toCategoryResponse(subCategory, false))
                .toList()
                : List.of();

        return JobCategoryResponse.builder()
                .name(request.getName())
                .description(request.getDescription())
                .id(request.getId())
                .slug(request.getSlug())
                .iconUrl(request.getIconUrl())
                .active(request.getActive())
                .parentId(request.getParentCategory()!=null ? request.getParentCategory().getId() : null)
                .parentName(request.getParentCategory()!=null ? request.getParentCategory().getName() : null)
                .subCategories(subCategories)
                .createdAt(request.getCreatedAt())
                .build();
    }
}
