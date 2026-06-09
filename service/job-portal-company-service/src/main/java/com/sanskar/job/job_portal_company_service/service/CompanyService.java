package com.sanskar.job.job_portal_company_service.service;

import com.sanskar.job.domain.CompanyStatus;
import com.sanskar.job.domain.CompanyType;
import com.sanskar.job.domain.IndustryType;
import com.sanskar.job.dto.request.CompanyRequest;
import com.sanskar.job.dto.response.CompanyResponse;
import com.sanskar.job.job_portal_company_service.model.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(Long ownerId, CompanyRequest req);

    CompanyResponse getCompanyById(Long id);

    CompanyResponse getMyCompany(Long ownerId);

    List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus);

    CompanyResponse updateCompany(Long companyId,Long ownerId,CompanyRequest req);

    CompanyResponse verifyCompany(Long companyId);

    void deleteCompany(Long companyId);

    CompanyResponse deactivateCompany(Long companyId);

    Company getCompanyEntityById(Long id);

}
