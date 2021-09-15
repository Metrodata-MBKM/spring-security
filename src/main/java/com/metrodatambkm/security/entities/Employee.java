package com.metrodatambkm.security.entities;

import com.metrodatambkm.security.entities.credentials.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "hire_date")
    @Nullable
    private Date hireDate;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "commission_pct")
    @Nullable
    private Double commissionPct;

    @JoinColumn(name = "job")
    @ManyToOne
    private Job job;

    @JoinColumn(name = "manager")
    @ManyToOne
    @Nullable
    private Employee manager;

    @JoinColumn(name = "department")
    @ManyToOne
    @Nullable
    private Department department;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Employee(String firstName, String lastName, String phone, @Nullable Date hireDate, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.hireDate = hireDate;
        this.user = user;
    }

    public Employee(Integer id, String firstName, String lastName, String email, String phone, @Nullable Date hireDate, Double salary, @Nullable Double commissionPct, Job job, @Nullable Employee manager, @Nullable Department department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissionPct = commissionPct;
        this.job = job;
        this.manager = manager;
        this.department = department;
    }
}