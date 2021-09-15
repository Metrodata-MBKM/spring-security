package com.metrodatambkm.security.dtos.response;

import com.metrodatambkm.security.dtos.EntityResponse;
import com.metrodatambkm.security.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class JobResponse implements EntityResponse<Job, JobResponse> {
    private Integer id;
    private String name;
    private double minSalary;
    private double maxSalary;

    public JobResponse(Job job) {
        this.id = job.getId();
        this.name = job.getName();
        this.minSalary = job.getMinSalary();
        this.maxSalary = job.getMaxSalary();
    }

    @Override
    public JobResponse create(Job job) {
        return new JobResponse(job);
    }
}
