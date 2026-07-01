package com.sanskar.job.job_portal_resume_service.repository;

import com.sanskar.job.job_portal_resume_service.model.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

    List<WorkExperience> findByResume_IdOrderByDisplayOrderAsc (Long resumeId);
}
