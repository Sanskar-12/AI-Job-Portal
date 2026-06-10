package com.sanskar.job.job_portal_company_service.model;

import com.sanskar.job.domain.CompanySize;
import com.sanskar.job.domain.CompanyStatus;
import com.sanskar.job.domain.CompanyType;
import com.sanskar.job.domain.IndustryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    @Column(unique = true)
    private String slug;

    private String tagline;

    private String description;

    private String logoUrl;

    private String coverImageUrl;

    private String website;

    private String email;

    private String phone;

    private Boolean verified=false;

    private Integer foundedYear;

    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    private CompanyType companyType;

    @Enumerated(EnumType.STRING)
    private IndustryType industryType;

    @Enumerated(EnumType.STRING)
    private CompanyStatus companyStatus;

    @Column(unique = true)
    private String registrationNumber;

    @Column(nullable = false,unique = true)
    private Long ownerId;

    @ElementCollection
    private List<SocialLink> socialLink=new ArrayList<>();

    private Boolean active=true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
