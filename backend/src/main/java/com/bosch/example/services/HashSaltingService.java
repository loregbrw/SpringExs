package com.bosch.example.services;

public interface HashSaltingService {
    String HashPassword(String password, String salt);
    String SaltHash();
} 
