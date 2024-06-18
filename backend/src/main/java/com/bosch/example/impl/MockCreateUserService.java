package com.bosch.example.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

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

    @Override
    public Optional<UserData> findId(Long id) {

        UserData findUser = null;

        for (UserData user : users) {
            if (user.getId().equals(id)) {
                findUser = user;
            }
        }

        return Optional.ofNullable(findUser);
    }
}
