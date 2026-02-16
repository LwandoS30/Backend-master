package com.relink.backend.service.implementation;

import com.relink.backend.enums.JobStatus;
import com.relink.backend.exception.JobNotFoundException;
import com.relink.backend.model.Job;
import com.relink.backend.model.Recruiter;
import com.relink.backend.record.CreateJobRequest;
import com.relink.backend.record.UpdateJobRequest;
import com.relink.backend.repository.JobRepository;
import com.relink.backend.service.JobService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository=jobRepository;
    }


    @Override
    public Job createJob(CreateJobRequest request,Recruiter recruiter) {
        Instant now= Instant.now();
        Job job=new Job();
        job.setJobTitle(request.title());
        job.setLocation(request.location());
        job.setDescription(request.description());
        job.setSalary(request.salary());
        job.setPostedDate(now);
        job.setStatus(JobStatus.OPEN);
        job.setClosingDate(request.closingDate());
        job.setRecruiter(recruiter);
        return jobRepository.save(job);
    }

    @Override
    public List<Job> listJobs() {

        return jobRepository.findAll(Sort.by(Sort.Direction.DESC,"postedDate"));
    }

    @Override
    public List<Job> listJobsbyRecruiter(Recruiter recruiter) {

        return jobRepository.findByRecruiterId(recruiter.getId());
    }

    @Override
    public Job updateJob(UUID id, UpdateJobRequest request) {
       Job job=jobRepository.findById(id).orElseThrow(()->new JobNotFoundException(id));

       job.setJobTitle(request.title());
       job.setDescription(request.description());
       job.setLocation(request.location());
       job.setSalary(request.salary());
       job.setStatus(request.status());
       job.setClosingDate(request.closingDAte());

       return jobRepository.save(job);
    }

    @Override
    public void deleteJob(UUID id) {
        jobRepository.deleteById(id);
    }


}
