package com.sanskar.job.job_portal_resume_service.model;

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
@Table(name = "project")
public class Project {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Column(nullable = false)
    private String title;

    private String description;

    @ElementCollection
    private List<String> technologies=new ArrayList<>();

    private String projectUrl;

    private String sourceCodeUrl;

    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isOngoing=false;

    @Column(nullable = false)
    @Builder.Default
    private Integer displayOrder=0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
