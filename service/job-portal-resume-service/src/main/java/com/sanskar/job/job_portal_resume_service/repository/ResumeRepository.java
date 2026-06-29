package com.sanskar.job.job_portal_resume_service.repository;

import com.sanskar.job.job_portal_resume_service.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByCandidateIdAndIsActiveTrue(Long id);

    Optional<Resume> findByCandidateIdAndIsDefaultTrue(Long id);
}
