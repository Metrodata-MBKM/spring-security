package com.metrodatambkm.security.models.hr_schema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metrodatambkm.security.models.credentials.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "hire_date", nullable = true)
    private Date hireDate;

    @Column(name = "salary", nullable = true)
    private Double salary;

    @Nullable
    @Column(name = "commission_pct", nullable = true)
    private Double commissionPct;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "managerId", fetch = FetchType.LAZY)
    private Collection<Employee> employeeCollection;

    @JsonIgnore
    @Nullable
    @JoinColumn(name = "manager_id", referencedColumnName = "employee_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee managerId;

    @OneToOne(mappedBy = "employee")
    private AppUser appUser;

    public Employee(String firstName, String lastName, String email, String phoneNumber, String hireDate, Double salary, @Nullable Double commissionPct, Job job, Department department, Employee manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = Date.valueOf(hireDate);
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.job = job;
        this.department = department;
        this.managerId = manager;
    }

    public Employee(String email) {
        this.email = email;
    }

    public Employee(String firstName, String lastName, String phoneNumber, Long department, Long job, Long manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.department = new Department(department);
        this.job = new Job(job);
        this.managerId = new Employee(manager);
    }

    public Employee(Long manager) {
        this.id = manager;
    }
}
