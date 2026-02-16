package com.relink.backend.service.implementation;

import com.relink.backend.model.JobApplication;
import com.relink.backend.record.CreateJobApplicationRequest;
import com.relink.backend.repository.JobApplicationRepository;
import com.relink.backend.service.JobApplicationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }


    @Override
    public JobApplication createJobApplication(CreateJobApplicationRequest jobApplicationRequest) {
        Instant now=Instant.now();

        JobApplication jobApplication=new JobApplication();

        jobApplication.setApplicantName(jobApplicationRequest.applicantName());
        jobApplication.setApplicantEmail(jobApplicationRequest.applicantEmail());
        jobApplication.setStatus(jobApplicationRequest.status());
        jobApplication.setAppliedAt(now);

        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public List<JobApplication> listJobApplication() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public List<JobApplication> listJobByKeyword(String keyword) {
        return List.of();
    }

}
