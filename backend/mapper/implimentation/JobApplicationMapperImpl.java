package com.relink.backend.mapper.implimentation;

import com.relink.backend.dto.JobApplicationDto;
import com.relink.backend.dto.fromfrontend.JobApplicationRequestDto;
import com.relink.backend.enums.JobApplicationStatus;
import com.relink.backend.mapper.JobApplicationMapper;
import com.relink.backend.model.JobApplication;
import com.relink.backend.record.CreateJobApplicationRequest;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationMapperImpl implements JobApplicationMapper {


    @Override
    public CreateJobApplicationRequest fromDto(JobApplicationRequestDto dto) {
        return new CreateJobApplicationRequest(
                dto.jobID(),
                JobApplicationStatus.PENDING,
                dto.applicantEmail(),
                dto.applicantName()
        );
    }

    @Override
    public JobApplicationDto toDto(JobApplication jobApplication) {
        return new JobApplicationDto(
                jobApplication.getApplicantName(),
                jobApplication.getApplicantEmail(),
                jobApplication.getAppliedAt()
        );
    }
}
