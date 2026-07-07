package com.sanskar.job.job_portal_resume_service.repository;

import com.sanskar.job.job_portal_resume_service.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByResume_IdOrderByDisplayOrderAsc(Long resumeId);
}
