/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models;

/**
 *
 * @author Asus
 */
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "jobs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Job {
    @Id
    @Column(name = "job_id", length = 10)
    private String id;

    @Column(name = "job_title", length = 35, nullable = false)
    private String title;

    @Column(name = "min_salary")
    private Double min_salary;

    @Column(name = "max_salary")
    private Double max_salary;

    @JsonBackReference
    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
    private Set<Employee> employee;
}
