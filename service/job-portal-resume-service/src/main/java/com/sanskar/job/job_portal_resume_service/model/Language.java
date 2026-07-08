package com.sanskar.job.job_portal_resume_service.model;

import com.sanskar.job.domain.LanguageProficiency;
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
@Table(name = "language")
public class Language {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resume resume;

    @Column(nullable = false)
    private String languageName;

    private LanguageProficiency proficiency;

    @Column(nullable = false)
    @Builder.Default
    private Integer displayOrder=0;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
