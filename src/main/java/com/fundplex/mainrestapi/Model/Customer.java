package com.fundplex.mainrestapi.Model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
// import java.util.ArrayList;
// import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fundplex.mainrestapi.loan.Loan;

@Entity
@Table(name = "customers")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String firstName;
    public String lastName;
    public String displayName;
    public String mobile;
    public String email;
    public Long status;
    public String company;
    public String address;
    public String village;
    public String city;
    public String state;
    public String zipcode;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "customer", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "loan", referencedColumnName = "id"))
    public Set<Loan> permissions = new HashSet<>();

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinTable(name = "customer_addresses", joinColumns = @JoinColumn(name =
    // "customer", referencedColumnName = "id"), inverseJoinColumns =
    // @JoinColumn(name = "addresses", referencedColumnName = "id"))

    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "company_id")
    // public Company company;
    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinTable(name = "customer_licence", joinColumns = @JoinColumn(name =
    // "customer", referencedColumnName = "id"), inverseJoinColumns =
    // @JoinColumn(name = "licence", referencedColumnName = "id"))
    // public Licence licence;

}
