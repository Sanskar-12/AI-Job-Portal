package com.sanskar.job.job_portal_resume_service.model;

import com.sanskar.job.domain.ProficiencyLevel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resume_skills")
public class ResumeSkill {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Column(nullable = false)
    private String skillName;

    private ProficiencyLevel proficiencyLevel=ProficiencyLevel.BEGINNER;

    private Integer yearsOfExperience;

    @Column(nullable = false)
    private Integer displayOrder=0;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
