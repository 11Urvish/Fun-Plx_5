package com.fundplex.mainrestapi.morgageItems;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fundplex.mainrestapi.loan.Loan;

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
@Table(name = "morgageItems")
public class MorgageItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String itemName;
    public Long itemQuantity;
    public String unitOfMeasurement;
    public String metal;
    public String purity;

    @ManyToOne
    @JoinColumn(name = "loanId")
    public Loan loan;

}
