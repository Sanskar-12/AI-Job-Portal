package com.sanskar.job.job_portal_company_service.service.impl;

import com.sanskar.job.domain.CompanyStatus;
import com.sanskar.job.domain.CompanyType;
import com.sanskar.job.domain.IndustryType;
import com.sanskar.job.dto.request.CompanyRequest;
import com.sanskar.job.dto.response.CompanyResponse;
import com.sanskar.job.job_portal_company_service.model.Company;
import com.sanskar.job.job_portal_company_service.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest req) {
        return null;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        return null;
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) {
        return null;
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        return List.of();
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) {
        return null;
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) {
        return null;
    }

    @Override
    public void deleteCompany(Long companyId) {

    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) {
        return null;
    }

    @Override
    public Company getCompanyEntityById(Long id) {
        return null;
    }
}
