/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.models.hr_schema;

/**
 * @author Asus
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Long id;

    @Column(length = 35, nullable = false)
    private String title;

    @Column(name = "min_salary")
    private double min_salary;

    @Column(name = "max_salary")
    private double max_salary;

    @JsonIgnore
    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Employee> employee;

    public Job(String title, double min_salary) {
        this.title = title;
        this.min_salary = min_salary;
    }

    public Job(Long job) {
        this.id = job;
    }
}
