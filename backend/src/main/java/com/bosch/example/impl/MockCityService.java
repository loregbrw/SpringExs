package com.bosch.example.impl;

import java.util.ArrayList;
import java.util.List;

import com.bosch.example.model.CityData;
import com.bosch.example.services.CityService;

public class MockCityService implements CityService {

    List<CityData> cities = new ArrayList<>();

    public MockCityService()
    {
        CityData city = new CityData();

        city.setName("Rio de Janeiro");
        city.setCountry("Brasil");
        city.setState("RJ");

        cities.add(city);
    }

    @Override
    public List<CityData> getAll() {
        return cities;
    }

    @Override
    public List<CityData> search(String value) {
        return cities.stream()
            .filter(c -> c.getName().contains(value))
            .toList();
    }
}
