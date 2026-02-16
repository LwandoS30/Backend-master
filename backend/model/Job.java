package com.relink.backend.model;

import com.relink.backend.enums.JobStatus;
import com.relink.backend.enums.JobType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id",updatable = false,nullable = false)
    private UUID id;

    @Column(name="jobTitle",nullable = false)
    private String jobTitle;

    @Column(name="companyName",nullable = false)
    private String companyName;

    @Column(name="description",nullable = false)
    private String description;

    @Column(name="location",nullable = false,length = 5000)
    private String location;

    @Column(name="salary")
    private String salary;

    @Enumerated(EnumType.STRING)
    @Column(name="status",nullable = false)
    private JobStatus status;

    @Column(name="postedDate",nullable = false,updatable = false)
    private Instant postedDate;

    @Column(name="closingDate",nullable = false,updatable = false)
    private LocalDateTime closingDate;

    @Enumerated(EnumType.STRING)
    @Column(name="jobType",nullable = false)
    private JobType jobType;

    @ManyToOne
    @JoinColumn(name="recruiter_id",nullable = false)
    private Recruiter recruiter;

    public Job() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }


    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + jobTitle + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", salary='" + salary + '\'' +
                ", status=" + status +
                ", postedDate=" + postedDate +
                ", closingDate=" + closingDate +
                '}';
    }
}
