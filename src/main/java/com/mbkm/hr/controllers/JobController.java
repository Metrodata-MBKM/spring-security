/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.models.hrschemas.Job;
import com.mbkm.hr.services.JobService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Asus
 */
@RestController
@RequestMapping("/job")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class JobController implements BaseController<Job, String> {

    @Autowired
    JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<Job> getAll() {
        return jobService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public Job getById(@PathVariable(value="id") String id) {
        try {
            return jobService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job with ID: " + id + " Not Found");
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public Job save(@RequestBody Job job) {
        if (jobService.getById(job.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Job with ID: " + job.getId() + " Is Already Exist");
        } else {
            return jobService.save(job);
        }
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public Job update(@PathVariable("id") String id, @RequestBody Job job) {
        if (!jobService.getById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job with ID: " + job.getId() + " Not Found");
        } else {
            return jobService.save(job);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    public String delete(@PathVariable(value="id") String id) {
        if (jobService.delete(id)) {
            return ("Job with ID: " + id + " Deleted Successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job with ID: " + id + " Not Found");
        }
    }

}
