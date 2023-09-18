package com.fundplex.mainrestapi.loanTransfer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loan_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoanItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long customerId;
    public String customerName;
    public Long loanNumber;
    public Long loanAmount;
    public Long interestRate;
    public Long status;

    @ManyToOne
    @JsonBackReference
    public LoanTransfer loanTransfer;

}
