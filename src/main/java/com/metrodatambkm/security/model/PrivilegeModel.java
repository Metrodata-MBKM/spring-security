package com.metrodatambkm.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "privilege")
//@Data @AllArgsConstructor @NoArgsConstructor
public class PrivilegeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany(mappedBy = "privilege", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<RoleModel> privilegeRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RoleModel> getPrivilegeRole() {
        return privilegeRole;
    }

    public void setPrivilegeRole(Set<RoleModel> privilegeRole) {
        this.privilegeRole = privilegeRole;
    }
}
