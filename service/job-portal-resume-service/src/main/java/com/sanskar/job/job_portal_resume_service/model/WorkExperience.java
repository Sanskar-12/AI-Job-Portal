package com.sanskar.job.job_portal_resume_service.model;

import com.sanskar.job.domain.JobType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "work_experiences")
public class WorkExperience {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Column(nullable = false)
    private String companyName;

    private String companyLogoUrl;

    @Column(nullable = false)
    private String jobTitle;

    @Enumerated(EnumType.STRING)
    private JobType employmentType;

    private String location;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    private Boolean isCurrentJob=false;

    private String description;

    @ElementCollection
    private List<String> technologies=new ArrayList<>();

    @Column(nullable = false)
    private Integer displayOrder=0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
