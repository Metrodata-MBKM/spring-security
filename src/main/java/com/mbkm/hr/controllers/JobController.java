/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.models.Job;
import com.mbkm.hr.services.JobService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Asus
 */
@Controller
public class JobController implements BaseController<Job, String>{

    @Autowired
    JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/job/")
    @ResponseBody
    public List<Job> getAll() {
        return jobService.getAll();
    }

    @GetMapping("/job/{id}")
    public Job getById(String id) {
        return (Job) jobService.getById(id).get();
    }

    @PostMapping
    public Job save(Job job) {
        return jobService.save(job);
    }

    @PutMapping
    public Job update(Job job) {
        return jobService.save(job);
    }

    @DeleteMapping("/job/{id}")
    public String delete(String id) {
        jobService.delete(id);
        return "Delete Successfully";
    }
    
    
    
    
    
}
