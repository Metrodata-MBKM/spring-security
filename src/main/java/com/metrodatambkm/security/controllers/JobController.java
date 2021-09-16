/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.models.hr_schema.Job;
import com.metrodatambkm.security.models.hr_schema.Region;
import com.metrodatambkm.security.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Asus
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<Job> getAllJob() {
        return jobService.getAll();
    }

    @PostMapping
    public Job save(@RequestBody Job job) {
        return jobService.save(job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable("id") Long id) {
        jobService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateJob(@PathVariable("id") Long id, @RequestBody Job job) {
        jobService.update(job);
    }

    @GetMapping("/{id}")
    public Job getJob(@PathVariable("id") Long id) {
        return jobService.getById(id);
    }
}
