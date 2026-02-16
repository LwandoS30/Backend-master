package com.relink.backend.controller;


import com.relink.backend.dto.JobDto;
import com.relink.backend.dto.fromfrontend.JobRequestDto;
import com.relink.backend.mapper.JobMapper;
import com.relink.backend.model.Job;
import com.relink.backend.model.Recruiter;
import com.relink.backend.record.CreateJobRequest;
import com.relink.backend.record.UpdateJobRequest;
import com.relink.backend.service.JobService;
import com.relink.backend.service.RecruiterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path= "/jobs")

public class JobController {

    private final JobService jobService;
    private final JobMapper jobMapper;
    private final RecruiterService recruiterService;

    public JobController(JobService jobService, JobMapper jobMapper,RecruiterService recruiterService) {
        this.jobService = jobService;
        this.jobMapper = jobMapper;
        this.recruiterService=recruiterService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<JobDto> createJob(
           @Valid @RequestBody JobRequestDto jobRequestDto
    ){
        UUID recruiterId=jobRequestDto.recruiterId();
        Recruiter recruiter=recruiterService.getRecruiterById(recruiterId);
        CreateJobRequest createJobRequest=jobMapper.fromDto(jobRequestDto);
        Job job=jobService.createJob(createJobRequest,recruiter);
        JobDto createdJobDto=jobMapper.toDto(job);

        return new ResponseEntity<>(createdJobDto, HttpStatus.CREATED);
    }

    @GetMapping(path="/All/Jobs")
    public ResponseEntity<List<JobDto>> listJobs(){

        List<Job> jobs=jobService.listJobs();
        List<JobDto> jobDto=jobs.stream().map(jobMapper::toDto).toList();

        return ResponseEntity.ok(jobDto);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<JobDto> updateJob(
            @PathVariable UUID id,
            @Valid @RequestBody JobRequestDto jobRequestDto
    ){

        UpdateJobRequest updateJobRequest=jobMapper.fromUpdateDto(jobRequestDto);
        Job job=jobService.updateJob(id,updateJobRequest);
        JobDto jobDto=jobMapper.toDto(job);

        return ResponseEntity.ok(jobDto);

    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable UUID id){
        jobService.deleteJob(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
