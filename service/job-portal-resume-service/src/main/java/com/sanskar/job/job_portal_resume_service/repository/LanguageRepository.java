package com.sanskar.job.job_portal_resume_service.repository;

import com.sanskar.job.job_portal_resume_service.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    List<Language> findByResume_IdOrderByDisplayOrderAsc(Long resumeId);
}
