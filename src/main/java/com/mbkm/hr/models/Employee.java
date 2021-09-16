/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author loisceka
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private Date hireDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "job_id")
    @ManyToOne
    private Job job;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "commission_pct")
    private Double commissionPct;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee manager;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "manager")
    private Set<Department> departments;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private User user;

    public Employee(String firstName, String lastName, String email, String phoneNumber, Date hireDate, Double salary, Double commissionPct, Job job, Department department, Employee manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.job = job;
        this.department = department;
        this.manager = manager;
    }

    public Employee(Integer id, String firstName, String lastName, String email, String phoneNumber, Date hireDate, Double salary, Double commissionPct, Job job, Department department, Employee manager) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.job = job;
        this.department = department;
        this.manager = manager;
    }

}
