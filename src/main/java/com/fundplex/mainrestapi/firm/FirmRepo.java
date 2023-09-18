package com.fundplex.mainrestapi.firm;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FirmRepo extends JpaRepository<Firm, Long> {

    public Optional<Firm> findById(Long id);

    @Query("SELECT f FROM Firm f WHERE lower(f.name) LIKE lower(concat('%', :keyword, '%'))")
    List<Firm> findByValue(@Param("keyword") String keyword);

}
