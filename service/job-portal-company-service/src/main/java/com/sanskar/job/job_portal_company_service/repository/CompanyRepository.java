package com.sanskar.job.job_portal_company_service.repository;

import com.sanskar.job.domain.CompanyStatus;
import com.sanskar.job.domain.CompanyType;
import com.sanskar.job.domain.IndustryType;
import com.sanskar.job.job_portal_company_service.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByOwnerId(Long ownerId);

    boolean existsByOwnerId(String ownerId);

    boolean existsByName(String name);

    boolean existsBySlug(String slug);

    boolean existsByRegistrationNumber(String registrationNumber);

    @Query("""
        SELECT c
        FROM Company c
        WHERE (:status IS NULL OR c.companyStatus = :status)
        AND (:companyType IS NULL OR c.companyType = :companyType)
        AND (:industryType IS NULL OR c.industryType = :industryType)
    """)
    List<Company> filterCompanies(
            @Param("status") CompanyStatus status,
            @Param("companyType") CompanyType type,
            @Param("industryType") IndustryType industry
    );
}
