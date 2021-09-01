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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Asus
 */
@RestController
public class JobController implements BaseController<Job, String> {

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
        try {
            return jobService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job with ID: " + id + " Not Found");
        }
    }

    @PostMapping
    public Job save(@RequestBody Job job) {
        if (getById(job.getId()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Job with ID: " + job.getId() + " Is Already Exist");
        } else {
            return jobService.save(job);
        }
    }

    @PutMapping
    public Job update(@RequestBody Job job) {
        if (jobService.getById(job.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Job with ID: " + job.getId() + " Not Found");
        } else {
            return jobService.save(job);
        }
    }

    @DeleteMapping("/job/{id}")
    public String delete(String id) {
        try {
            jobService.delete(id);
            return ("Job with ID: " + id + "Deleted Successfully");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job with ID: " + id + " Not Found");
        }
    }

}
