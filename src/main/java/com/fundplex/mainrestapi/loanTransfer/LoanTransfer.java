package com.fundplex.mainrestapi.loanTransfer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fundplex.mainrestapi.firm.Firm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loan_transfers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @OneToOne
    public Firm firm;

    public Long bundleNumber;
    public Long amount;
    public Long interestRate;
    public Long status;
    public Long transferDate;
    public Long totalWeight;
    public Long totalItem;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    public List<LoanItem> loanItems = new ArrayList<>();
}
