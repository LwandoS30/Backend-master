package com.relink.backend.service;

import com.relink.backend.model.JobApplication;
import com.relink.backend.record.CreateJobApplicationRequest;

import java.util.List;

public interface JobApplicationService {

    JobApplication createJobApplication(CreateJobApplicationRequest JobApplicationRequest);

    List<JobApplication> listJobApplication();

    List<JobApplication> listJobByKeyword(String keyword);
}
