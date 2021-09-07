/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.models.Job;
import com.metrodatambkm.security.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gabri
 */
@Service
public class JobService extends CRUDService<JobRepository, Job, String>{
    @Autowired
    public JobService(JobRepository jobRepository){
        super.repository = jobRepository;
    }
}
