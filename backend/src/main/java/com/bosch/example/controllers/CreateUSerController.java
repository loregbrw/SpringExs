package com.bosch.example.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.bosch.example.dto.UserDto;
import com.bosch.example.model.UserData;
import com.bosch.example.services.UserService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class CreateUSerController {

    @Autowired
    UserService service;

    @PostMapping("create")
    public String getMethodName(@RequestBody UserDto param) {
        Matcher regexEmail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(param.email());
        Matcher regexPassword = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$").matcher(param.password());

        if (param.username().isEmpty() || param.username().length() < 4) {
            return "Usuário inválido";
        }

        if (param.email().isEmpty() || param.email().length() < 4 || !regexEmail.matches()) {
            return "E-mail inválido";
        }

        if (service.findName(param.username()).size() != 0) {
            return "Usuário já está em uso!";
        }

        if (service.findEmail(param.email()).size() != 0) {
            return "E-mail já está em uso!";
        }

        if (!regexPassword.matches()) {
            return "Senha inválida!";
        }

        service.save(new UserData(param.username(), param.password(), param.email()));
        return "Salvo com sucesso!";
    }
}
