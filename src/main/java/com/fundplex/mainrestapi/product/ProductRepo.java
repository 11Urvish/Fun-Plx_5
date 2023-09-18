package com.fundplex.mainrestapi.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

    public Optional<Product> findById(Long id);

}
