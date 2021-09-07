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
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @Column(name = "job_id", length = 10)
    private String id;

    @Column(name = "job_title", length = 35, nullable = false)
    private String title;

    @Column(name = "min_salary")
    private double min_salary;

    @Column(name = "max_salary")
    private double max_salary;

    @OneToMany(mappedBy = "job", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Employee> employee;

    public Job(String id, String title, double min_salary, double max_salary) {
        this.id = id;
        this.title = title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }

}
