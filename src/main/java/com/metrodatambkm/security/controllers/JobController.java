/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.models.hr_schema.Job;
import com.metrodatambkm.security.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Asus
 */
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;
}
