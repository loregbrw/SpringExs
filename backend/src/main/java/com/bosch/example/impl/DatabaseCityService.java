package com.bosch.example.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bosch.example.model.CityData;
import com.bosch.example.repositories.CityJpaRepository;
import com.bosch.example.services.CityService;

public class DatabaseCityService implements CityService {

    @Autowired
    CityJpaRepository repo;

    @Override
    public List<CityData> getAll() {
        return repo.findAll();
    }

    @Override
    public List<CityData> search(String value) {
        return repo.findByNameContaining(value);
    }
    
}
