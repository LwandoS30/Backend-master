package com.relink.backend.mapper;

import com.relink.backend.dto.JobApplicationDto;
import com.relink.backend.dto.fromfrontend.JobApplicationRequestDto;
import com.relink.backend.model.JobApplication;
import com.relink.backend.record.CreateJobApplicationRequest;

public interface JobApplicationMapper {

    CreateJobApplicationRequest fromDto(JobApplicationRequestDto dto);

    JobApplicationDto toDto(JobApplication jobApplication);
}
