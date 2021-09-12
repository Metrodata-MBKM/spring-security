/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.models.hr_schema;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "departments")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long id;
    
    @Column(length = 30)
    private String name;
    
    @Nullable
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    @JsonBackReference
    private Set<Employee> employees;

    public Department(String name) {
        this.name = name;
    }

    public Department(Long id) {
        this.id = id;
    }
}
