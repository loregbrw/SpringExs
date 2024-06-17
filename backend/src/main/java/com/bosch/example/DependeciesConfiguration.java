package com.bosch.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.bosch.example.impl.BcryptService;
import com.bosch.example.impl.DatabaseCreateUserService;
import com.bosch.example.impl.DefaultCPFValidator;
import com.bosch.example.impl.MockCityService;
import com.bosch.example.services.CPFValidator;
import com.bosch.example.services.CityService;
import com.bosch.example.services.HashSaltingService;
import com.bosch.example.services.UserService;

@Configuration
public class DependeciesConfiguration {
    
    @Bean
    @Scope("singleton")
    public CPFValidator cpfValidator() {
        return new DefaultCPFValidator();
    }

    @Bean
    @Scope("singleton")
    public CityService cityService() {
        return new MockCityService();
    }

    @Bean
    @Scope("singleton")
    public UserService userService() {
        return new DatabaseCreateUserService();
    }

    @Bean
    @Scope("singleton")
    public HashSaltingService hashSaltingService() {
        return new BcryptService();
    }
}
   
