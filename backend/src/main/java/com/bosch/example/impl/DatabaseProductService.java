package com.bosch.example.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bosch.example.model.ProductData;
import com.bosch.example.repositories.ProductJpaRepository;
import com.bosch.example.services.ProductServer;

public class DatabaseProductService implements ProductServer {
    
    @Autowired
    ProductJpaRepository repo;

    @Override
    public void save(ProductData product) {
        repo.save(product);        
    }

}
