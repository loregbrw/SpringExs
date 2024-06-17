package com.bosch.example.impl;

import java.util.ArrayList;
import java.util.List;

import com.bosch.example.model.UserData;
import com.bosch.example.services.UserService;

public class MockCreateUserService implements UserService {
    List<UserData> users = new ArrayList<>();

    @Override
    public void save(UserData user) {
        users.add(user);
    }

    @Override
    public List<UserData> findName(String name) {
        List<UserData> allUsers = new ArrayList<>();

        for (UserData user : users) {
            if (user.getUsername().equals(name)) {
                allUsers.add(user);
            }
        }

        return allUsers;
    }

    @Override
    public List<UserData> findEmail(String email) {
        List<UserData> allUsers = new ArrayList<>();

        for (UserData user : users) {
            if (user.getEmail().equals(email)) {
                allUsers.add(user);
            }
        }

        return allUsers;
    }
}
