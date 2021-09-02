/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.sql.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Manager {
    @Id
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "salary")
    private Double salary;

    @Nullable
    @Column(name = "commission_pct")
    private Double commissionPct;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "department_id")
    private Department department;
    
    @OneToMany(mappedBy = "manager")
    @JsonBackReference
    private Set<Department> departments;
}
