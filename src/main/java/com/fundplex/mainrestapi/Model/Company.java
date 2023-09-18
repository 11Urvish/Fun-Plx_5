package com.fundplex.mainrestapi.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name = "company")

public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String companyName;

    public String companyCode;

    public String companyType;

    public Long status;

    public String email;

    public String phone;

    public String website;

    public String address;

    public String city;

    public String state;

    public String country;

    public String zipcode;

    // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinTable(name = "company_users", joinColumns = @JoinColumn(name =
    // "company", referencedColumnName = "id"), inverseJoinColumns =
    // @JoinColumn(name = "users", referencedColumnName = "id"))
    // public Set<User> users = new HashSet<>();

    // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // @JoinTable(name = "company_customers", joinColumns = @JoinColumn(name =
    // "company", referencedColumnName = "Id"), inverseJoinColumns =
    // @JoinColumn(name = "customers", referencedColumnName = "customerId"))
    // public Set<Customer> customers = new HashSet<>();

}
