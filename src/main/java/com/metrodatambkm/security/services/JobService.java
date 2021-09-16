/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.models.hr_schema.Job;
import com.metrodatambkm.security.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Asus
 */
@Service
public class JobService {


    @Autowired
    private JobRepository jobRepository;

    public Job findById(Long job) {
        return jobRepository.getById(job);
    }

    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public void delete(Long id) {
        jobRepository.deleteById(id);
    }

    public void update(Job job) {
        jobRepository.save(job);
    }

    public Job getById(Long id) {
        return jobRepository.getById(id);
    }
}
