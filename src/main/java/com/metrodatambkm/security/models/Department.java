/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

/**
 *
 * @author gabri
 */
@Entity
@Table(name = "departments")
@Data
@Getter
@Setter
public class Department {
    @Id
    @Column(name = "department_id")
    private Integer id;
    
    @Column(name = "department_name", length = 30)
    private String name;
    
    @Nullable
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    
//    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "department")
    @JsonBackReference
    private Set<Employee> employees;
}
