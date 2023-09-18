// package com.fundplex.mainrestapi.Model;

// import java.util.Date;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToOne;
// import javax.persistence.Table;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import lombok.ToString;

// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @ToString
// @Entity
// @Table(name = "licence")
// public class Licence {
// @Id
// @GeneratedValue(strategy = GenerationType.AUTO)
// public Long id;
// public String licenceNumber;
// public String licenceType;
// public Date licenceExpiryDate = new Date();
// public String district;
// public String taluka;
// public String status;
// @OneToOne
// @JoinColumn(name = "customer_id")
// public Customer customer;

// }
