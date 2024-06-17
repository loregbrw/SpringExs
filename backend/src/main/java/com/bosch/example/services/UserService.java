package com.bosch.example.services;

import java.util.List;

import com.bosch.example.model.UserData;

public interface UserService {
    void save(UserData user);

    List<UserData> findName(String username);
    List<UserData> findEmail(String email);
}