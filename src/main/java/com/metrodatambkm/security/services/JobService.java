package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.request.JobRequest;
import com.metrodatambkm.security.dtos.response.JobResponse;
import com.metrodatambkm.security.entities.Job;
import com.metrodatambkm.security.repositories.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class JobService extends CRUDService<Job, JobRequest, JobResponse, Integer>{
    public JobService(JobRepository repository) {
        super.repository = repository;
    }

    @Override
    public Job convert(JobRequest request) {
        return new Job(
                request.getId(),
                request.getName(),
                request.getMinSalary(),
                request.getMaxSalary()
        );
    }
}
