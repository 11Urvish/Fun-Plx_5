package com.fundplex.mainrestapi.Model;

import lombok.*;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String name;

    public String status;

    public String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_permissions", referencedColumnName = "id"))
    public Set<RolePermission> permissions = new HashSet<>();

    // @ManyToOne
    // @JsonIgnore
    // @JoinColumn(name = "user_id")
    // public User user;
    private List<GrantedAuthority> getGrantedAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RolePermission rolePermissions : permissions) {
            authorities.add( new SimpleGrantedAuthority(rolePermissions.getPermissions()));
        }
        return authorities;
    }
}
