package com.relink.backend.mapper;

import com.relink.backend.dto.JobDto;
import com.relink.backend.dto.fromfrontend.JobRequestDto;
import com.relink.backend.model.Job;
import com.relink.backend.record.CreateJobRequest;
import com.relink.backend.record.UpdateJobRequest;

public interface JobMapper {

    CreateJobRequest fromDto(JobRequestDto dto);

    UpdateJobRequest fromUpdateDto(JobRequestDto dto);

    JobDto toDto(Job job);

}
