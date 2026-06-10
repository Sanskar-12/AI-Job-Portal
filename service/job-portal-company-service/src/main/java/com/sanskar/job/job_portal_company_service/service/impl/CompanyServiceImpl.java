package com.sanskar.job.job_portal_company_service.service.impl;

import com.sanskar.job.domain.CompanyStatus;
import com.sanskar.job.domain.CompanyType;
import com.sanskar.job.domain.IndustryType;
import com.sanskar.job.dto.request.CompanyRequest;
import com.sanskar.job.dto.response.CompanyResponse;
import com.sanskar.job.dto.response.SocialLinkResponse;
import com.sanskar.job.job_portal_company_service.mapper.CompanyMapper;
import com.sanskar.job.job_portal_company_service.model.Company;
import com.sanskar.job.job_portal_company_service.model.SocialLink;
import com.sanskar.job.job_portal_company_service.repository.CompanyRepository;
import com.sanskar.job.job_portal_company_service.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(Long ownerId, CompanyRequest req) throws Exception {
        if(companyRepository.existsByOwnerId(String.valueOf(ownerId))) {
            throw new Exception("You already have a company registered. Only one company per account is allowed");
        }
        if(companyRepository.existsByName(req.getName())) {
            throw new Exception("Company already exists. Please choose a different name");
        }
        if(req.getRegistrationNumber()!=null && companyRepository.existsByRegistrationNumber(req.getRegistrationNumber())) {
            throw new Exception("Company already exists. Please choose a different registration number");
        }

        String slug=generateUniqueSlug(req.getName());

        Company company=Company.builder()
                .name(req.getName())
                .slug(slug)
                .tagline(req.getTagline())
                .description(req.getDescription())
                .logoUrl(req.getLogoUrl())
                .coverImageUrl(req.getCoverImageUrl())
                .website(req.getWebsite())
                .email(req.getEmail())
                .phone(req.getPhone())
                .foundedYear(req.getFoundedYear())
                .companySize(req.getCompanySize())
                .companyType(req.getCompanyType())
                .industryType(req.getIndustryType())
                .registrationNumber(req.getRegistrationNumber())
                .ownerId(ownerId)
                .socialLink(mapSocialLinks(req.getSocialLinks()))
                .build();

        Company savedCompany=companyRepository.save(company);

        return CompanyMapper.mapToResponse(savedCompany);
    }

    private List<SocialLink> mapSocialLinks(List<SocialLinkResponse> socialLinks) {
        if(socialLinks==null || socialLinks.isEmpty()) {
            return new ArrayList<SocialLink>();
        }
        return socialLinks.stream().map((s)->SocialLink.builder()
                .url(s.getUrl())
                .platform(s.getPlatform())
                .build()).collect(Collectors.toList());
    }

    private String generateUniqueSlug(String name) {
        String base=name.toLowerCase().replaceAll("[^a-z0-9\\s-]]","")
                .trim().replaceAll("[\\s-]","-");

        if(!companyRepository.existsBySlug(base)) {
            return base;
        }

        int counter=1;
        while(companyRepository.existsBySlug(base+"-"+counter)) {
            counter++;
        }

        return base+"-"+counter;
    }

    @Override
    public CompanyResponse getCompanyById(Long id) throws Exception {
        Company company=companyRepository.findById(id).orElseThrow(()->new Exception("Company not found with id"));

        return CompanyMapper.mapToResponse(company);
    }

    @Override
    public CompanyResponse getMyCompany(Long ownerId) throws Exception {
        Company company=companyRepository.findByOwnerId(ownerId).orElseThrow(()->new Exception("Company not exist for owner "+ownerId));

        return CompanyMapper.mapToResponse(company);
    }

    @Override
    public List<CompanyResponse> getAllCompanies(CompanyType companyType, IndustryType industryType, CompanyStatus companyStatus) {
        return companyRepository.filterCompanies(
                companyStatus,
                companyType,
                industryType
        ).stream().map(CompanyMapper::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, Long ownerId, CompanyRequest req) throws Exception {
        Company company=getCompanyEntityById(companyId);

        if(!company.getName().equals(req.getName()) && companyRepository.existsByName(req.getName())) {
            throw new Exception("Company already exists. Please choose a different name");
        }
        if(req.getRegistrationNumber()!=null && !company.getRegistrationNumber().equals(req.getRegistrationNumber()) && companyRepository.existsByRegistrationNumber(req.getRegistrationNumber())) {
            throw new Exception("Company already exists. Please choose a different registration number");
        }

        company.setName(req.getName());
        company.setTagline(req.getTagline());
        company.setDescription(req.getDescription());
        company.setLogoUrl(req.getLogoUrl());
        company.setCoverImageUrl(req.getCoverImageUrl());
        company.setWebsite(req.getWebsite());
        company.setEmail(req.getEmail());
        company.setPhone(req.getPhone());
        company.setFoundedYear(req.getFoundedYear());
        company.setCompanySize(req.getCompanySize());
        company.setCompanyType(req.getCompanyType());
        company.setIndustryType(req.getIndustryType());
        company.setRegistrationNumber(req.getRegistrationNumber());
        company.setSocialLink(mapSocialLinks(req.getSocialLinks()));

        Company savedCompany=companyRepository.save(company);

        return CompanyMapper.mapToResponse(savedCompany);
    }

    @Override
    public CompanyResponse verifyCompany(Long companyId) throws Exception {
        Company company=getCompanyEntityById(companyId);

        company.setCompanyStatus(CompanyStatus.ACTIVE);
        company.setVerified(true);

        return CompanyMapper.mapToResponse(companyRepository.save(company));
    }

    @Override
    public void deleteCompany(Long companyId, Long ownerId) throws Exception {
        Company company=getCompanyEntityById(companyId);

        verifyOwner(company,ownerId);

        companyRepository.delete(company);
    }

    private void verifyOwner(Company company, Long ownerId) throws Exception {
        if(!company.getOwnerId().equals(ownerId)) {
            throw new Exception("You are not the owner of this company");
        }
    }

    @Override
    public CompanyResponse deactivateCompany(Long companyId) throws Exception {
        Company company=getCompanyEntityById(companyId);

        company.setCompanyStatus(CompanyStatus.SUSPENDED);
        company.setVerified(false);

        return CompanyMapper.mapToResponse(companyRepository.save(company));
    }

    @Override
    public Company getCompanyEntityById(Long id) throws Exception {
        return companyRepository.findById(id).orElseThrow(()->new Exception("Company not found with id"));
    }
}
