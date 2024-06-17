package com.bosch.example.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bosch.example.dto.PassDto;
import com.bosch.example.model.UserData;
import com.bosch.example.services.UserService;

@RestController
public class PassUserController {
    @Autowired
    UserService service;

    @PatchMapping("changepassword")
    public String savePassword(@RequestBody PassDto param) {

        UserData user = service.findName(param.username()).get(0);
        
        if (param.username().isEmpty() || service.findName(param.username()).size() == 0) {
            return "Usuário inválido";
        }

        if (!user.getPassword().equals(param.password())) {
            return "Senha inválida!";
        }

        if (!param.newPassword().equals(param.repeatPassword())) {
            return "A senha e a confirmação precisam ser iguais!";
        }

        Matcher regexPassword = Pattern
                .compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
                .matcher(param.password());

        if (!regexPassword.matches()) {
            return "Nova senha inválida!";
        }

        user.setPassword(param.newPassword());

        user.getPassword();

        service.save(user);
  
        return "Senha alterada com sucesso!";
    }
}
