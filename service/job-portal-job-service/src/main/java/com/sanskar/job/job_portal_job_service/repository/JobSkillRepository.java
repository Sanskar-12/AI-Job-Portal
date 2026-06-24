package com.sanskar.job.job_portal_job_service.repository;

import com.sanskar.job.job_portal_job_service.model.JobSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSkillRepository extends JpaRepository<JobSkill, Long> {

    List<JobSkill> findByActiveTrue();

    Boolean existsByName(String name);

    Boolean existsBySlug(String slug);
}
