package com.relink.backend.controller;

import com.relink.backend.dto.JobApplicationDto;
import com.relink.backend.dto.JobDto;
import com.relink.backend.dto.fromfrontend.JobApplicationRequestDto;
import com.relink.backend.mapper.JobApplicationMapper;
import com.relink.backend.model.Job;
import com.relink.backend.model.JobApplication;
import com.relink.backend.record.CreateJobApplicationRequest;
import com.relink.backend.record.CreateJobRequest;
import com.relink.backend.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/application")
public class JobApplicationController {
   private final JobApplicationMapper jobApplicationMapper;
   private JobApplicationService jobApplicationService;


    public JobApplicationController(JobApplicationMapper jobApplicationMapper,
                                    JobApplicationService jobApplicationService) {
        this.jobApplicationMapper = jobApplicationMapper;
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    ResponseEntity<JobApplicationDto> createJobApplication(
            @Valid @RequestBody JobApplicationRequestDto requestDto
            ){
        CreateJobApplicationRequest request=jobApplicationMapper.fromDto(requestDto);
        JobApplication jobApplication= jobApplicationService.createJobApplication(request);
        JobApplicationDto createdJobApplication=jobApplicationMapper.toDto(jobApplication);

        return new ResponseEntity<>(createdJobApplication, HttpStatus.CREATED);

    }

    @GetMapping
    ResponseEntity<List<JobApplicationDto>> listJobApplication(
            @RequestParam(required = false) String keyword)
    {

        List<JobApplication> jobApplications;

        if(keyword != null && !keyword.isEmpty()){
            jobApplications = jobApplicationService.listJobByKeyword(keyword);
        }
        else {
            jobApplications = jobApplicationService.listJobApplication();
        }

        List<JobApplicationDto> jobApplicationDtos=jobApplications.stream()
                .map(jobApplicationMapper::toDto)
                .toList();

        return ResponseEntity.ok(jobApplicationDtos);

    }

//    @GetMapping
//    ResponseEntity<List<JobApplicationDto>> listJobApplication(String keyword){
//
//        List<JobApplication> jobApplications=jobApplicationService.listJobApplication();
//        List<JobApplicationDto> jobApplicationDtos=jobApplications.stream().map(jobApplicationMapper::toDto).toList();
//
//        return ResponseEntity.ok(jobApplicationDtos);
//
//    }

}
