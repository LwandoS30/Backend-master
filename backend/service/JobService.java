package com.relink.backend.service;

import com.relink.backend.model.Job;
import com.relink.backend.model.Recruiter;
import com.relink.backend.record.CreateJobRequest;
import com.relink.backend.record.UpdateJobRequest;

import java.util.List;
import java.util.UUID;

public interface JobService {

    Job createJob(CreateJobRequest request, Recruiter recruiter);

    List<Job> listJobs();

    List<Job> listJobsbyRecruiter(Recruiter recruiter);

    Job updateJob(UUID id, UpdateJobRequest request);

    void deleteJob(UUID id);



}
