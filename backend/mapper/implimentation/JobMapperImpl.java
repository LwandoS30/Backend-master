package com.relink.backend.mapper.implimentation;

import com.relink.backend.dto.JobDto;
import com.relink.backend.dto.fromfrontend.JobRequestDto;
import com.relink.backend.mapper.JobMapper;
import com.relink.backend.model.Job;
import com.relink.backend.record.CreateJobRequest;
import com.relink.backend.record.UpdateJobRequest;
import org.springframework.stereotype.Component;

@Component
public class JobMapperImpl implements JobMapper {
    @Override
    public CreateJobRequest fromDto(JobRequestDto dto) {
       return new CreateJobRequest(
               dto.title(),
               dto.description(),
               dto.location(),
               dto.closingDate(),
               dto.salary()
       );
    }

    @Override
    public UpdateJobRequest fromUpdateDto(JobRequestDto dto) {
        return new UpdateJobRequest(
                dto.title(),
                dto.description(),
                dto.location(),
                dto.closingDate(),
                dto.salary(),
                dto.status()
        );
    }

    @Override
    public JobDto toDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getJobTitle(),
                job.getLocation(),
                job.getDescription(),
                job.getSalary(),
                job.getStatus(),
                job.getClosingDate()
        );
    }
}
