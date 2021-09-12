package com.mbkm.hr.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;
    
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Basic(optional = false)
    @Column(name = "hire_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date hireDate;
    
    @Basic(optional = false)
    @Column(name = "salary")
    private Double salary;
    @Column(name = "commission_pct")
    private Double commissionPct;
    
    @JsonBackReference
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private Set<Department> departments;
    
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Job job;
    
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
    
    @JsonBackReference
    @OneToMany(mappedBy = "managerId", fetch = FetchType.LAZY)
    private Set<Employee> employees;
    
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee managerId;
    
    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private User user;

    public Employee(String firstName, String lastName, String email, String phoneNumber, Date hireDate, Double salary, Double commissionPct, Job job, Department department, Employee managerId) {
//        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.job = job;
        this.department = department;
        this.managerId = managerId;
    }
    
    

    

}
