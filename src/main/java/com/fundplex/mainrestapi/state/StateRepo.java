package com.fundplex.mainrestapi.state;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<State, Long> {

    public Optional<State> findById(Long id);

    public List<State> findByCountryId(Long country_id);

}
