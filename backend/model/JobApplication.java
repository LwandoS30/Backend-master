package com.relink.backend.model;

import com.relink.backend.enums.JobApplicationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="job_applications")
@Getter
@Setter
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="job_application_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name="User_id",nullable = true)
    private Job job;

    @Enumerated(EnumType.STRING)
    @Column(name="recruiter_id",nullable=false)
    private JobApplicationStatus status;

    //To be replaced by User
    @Column(name="applicant_name",nullable = false)
    private String applicantName;

    @Column(name="applicant_email")
    private String applicantEmail;

    //The two above must be replaced

    private Instant appliedAt=Instant.now();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JobApplication that = (JobApplication) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

//