package com.fundplex.mainrestapi.morgageItems;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MorgageItemsRepo extends JpaRepository<MorgageItems, Long> {
    public Optional<MorgageItems> findById(Long id);
}
