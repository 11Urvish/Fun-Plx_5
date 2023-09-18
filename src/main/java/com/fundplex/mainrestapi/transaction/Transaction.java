package com.fundplex.mainrestapi.transaction;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fundplex.mainrestapi.Model.Customer;
import com.fundplex.mainrestapi.loan.Loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String transactionId;

    @ManyToOne
    @JsonBackReference
    public Customer customerId;

    @ManyToOne
    @JsonBackReference
    public Loan loanId;
    public Long amount;
    public Long interestAmount;
    public Long principalAmount;
    public Long balance;
    public Long transactionType;
    public boolean isInterestPaid;
    public String precedingHash;
    public String hash;
    public Date paymentDate = new Date();
    public String description;
    public Date timestamp = new Date();

}
