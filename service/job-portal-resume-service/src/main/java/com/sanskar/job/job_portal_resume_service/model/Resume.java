package com.sanskar.job.job_portal_resume_service.model;

import com.sanskar.job.domain.ResumeTemplate;
import com.sanskar.job.domain.ResumeVisibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resumes")
public class Resume {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long candidateId;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResumeTemplate template=ResumeTemplate.PROFESSIONAL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResumeVisibility visibility=ResumeVisibility.PUBLIC;

    @Column(nullable = false)
    private Boolean isDefault=false;

    @Embedded
    private PersonalInfo personalInfo;

    private String summary;

    private Integer completionScore=0;

    private Boolean isActive=true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
