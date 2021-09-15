package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.request.JobRequest;
import com.metrodatambkm.security.dtos.response.JobResponse;
import com.metrodatambkm.security.entities.Job;
import com.metrodatambkm.security.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController extends BasicRestController<Job, JobRequest, JobResponse, Integer>{

    @Autowired
    public JobController(JobService service) {
        super.service = service;
    }
}
