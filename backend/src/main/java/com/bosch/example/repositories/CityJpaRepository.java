package com.bosch.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosch.example.model.CityData;
import java.util.List;

@Repository
public interface CityJpaRepository extends JpaRepository<CityData, Long>{
    List<CityData> findByNameContaining(String name);
}