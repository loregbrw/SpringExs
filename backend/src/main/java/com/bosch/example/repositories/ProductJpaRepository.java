package com.bosch.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosch.example.model.ProductData;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductData, Long> {}
