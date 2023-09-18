package com.fundplex.mainrestapi.state;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "states")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public Long countryId;

    public String name;
    public String code;
    public Long status;

}
