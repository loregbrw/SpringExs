package com.bosch.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CityData")
public class CityData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CityName")
    private String name;

    @Column(name = "CityCountry")
    private String country;

    @Column(name = "CityState")
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String cityName) {
        this.name = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String cityCountry) {
        this.country = cityCountry;
    }

    public String getState() {
        return state;
    }

    public void setState(String cityState) {
        this.state = cityState;
    }

}