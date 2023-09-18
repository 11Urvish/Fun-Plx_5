package com.fundplex.mainrestapi.loan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fundplex.mainrestapi.Model.Customer;
import com.fundplex.mainrestapi.morgageItems.MorgageItems;
import com.fundplex.mainrestapi.transaction.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "loanDetails")

public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    @JsonBackReference
    public Customer customerId;
    public Long loanNumber;
    // public String loanType;
    public Long loanAmount;
    // public Long principalAmount;
    public Long interestRate;
    // public Long status;
    public Long loanPeriodYears;
    public Long loanPeriodMonths;

    public Date issueDate = new Date();
    public Date maturityDate = new Date();
    // public Date paidTillDate = new Date();
    // public Long totalPaidPrincialAmount;
    // public Long totalPaidInterestAmount;
    // public boolean isLoanTransfer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "loan_morgageItems", joinColumns = @JoinColumn(name = "loanDetails", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "morgageItems", referencedColumnName = "id"))
    public List<MorgageItems> morgageItems = new ArrayList<>();

    public String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(name = "loan_morgageItems", joinColumns = @JoinColumn(name = "loanDetails", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "transaction", referencedColumnName = "id"))
    public List<Transaction> transactions = new ArrayList<>();

}
