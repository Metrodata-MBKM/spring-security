package com.metrodatambkm.security.models.credentials;

import com.metrodatambkm.security.models.Privilege;
import com.metrodatambkm.security.models.Role;
import com.metrodatambkm.security.models.hr_schema.Employee;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean locked = false;
    private Boolean enabled = false;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "employee_id")
    private Employee employee;

    public AppUser(String username,
                   String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String password, Role user) {
        this.username = username;
        this.password = password;
        this.roles = Collections.singletonList(user);
    }

    public AppUser(String username, String password, Employee employee, Role role) {
        this.username = username;
        this.password = password;
        this.employee = employee;
        this.roles = Collections.singletonList(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Get Authority : ");
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {
        System.out.println("Testing : ");
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add("ROLE_" + role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName().toUpperCase());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
