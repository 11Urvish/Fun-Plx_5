// package com.fundplex.mainrestapi.Model;

// import lombok.*;

// import javax.persistence.*;

// @Entity
// @Table(name = "addresses")

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
// public class Address {
// @Id
// @GeneratedValue(strategy = GenerationType.AUTO)
// public Long id;
// public String address;
// public String village;
// public String city;
// public String state;
// public String zipcode;
// @OneToOne
// @JoinColumn(name = "customer_id")
// public Customer customer;
// }
