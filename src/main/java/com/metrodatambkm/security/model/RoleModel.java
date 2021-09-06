package com.metrodatambkm.security.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<UserModel> userRole;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_privilege", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<PrivilegeModel> privilege;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserModel> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserModel> userRole) {
        this.userRole = userRole;
    }

    public List<PrivilegeModel> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<PrivilegeModel> privilege) {
        this.privilege = privilege;
    }
}
