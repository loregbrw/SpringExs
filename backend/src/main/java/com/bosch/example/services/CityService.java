package com.bosch.example.services;

import java.util.List;

import com.bosch.example.model.CityData;

public interface CityService {
    List<CityData> getAll();
    List<CityData> search(String value);
}