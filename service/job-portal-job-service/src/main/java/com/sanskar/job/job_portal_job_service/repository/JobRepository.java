package com.sanskar.job.job_portal_job_service.repository;

import com.sanskar.job.job_portal_job_service.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    List<Job> findByCompanyId(Long companyId);
}
