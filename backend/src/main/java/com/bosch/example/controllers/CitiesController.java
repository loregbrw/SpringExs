package com.bosch.example.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.bosch.example.model.CityData;
import com.bosch.example.services.CityService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class CitiesController {
    
    @Autowired
    CityService service;

    @GetMapping("cities/{param}")
    public List<CityData> searchCities(@PathVariable String param) {
        return service.search(param);
    }
    
    @GetMapping("cities")
    public List<CityData> getAllCities() {
        return service.getAll();
    }

}