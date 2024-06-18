package com.bosch.example.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bosch.example.model.UserData;
import com.bosch.example.repositories.CreateUserJpaRepository;
import com.bosch.example.services.UserService;

public class DatabaseCreateUserService implements UserService {
    
    @Autowired
    CreateUserJpaRepository repo;

    @Override
    public void save(UserData user) {
        repo.save(user);
    }

    @Override
    public List<UserData> findName(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public List<UserData> findEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<UserData> findId(Long id) {
        return repo.findById(id);
    }
}
