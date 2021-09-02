package com.mbkm.hr.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
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
    @JoinColumn(name = "department_id")
    private Department department;

}
