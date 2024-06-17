package com.bosch.example.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bosch.example.dto.AuthDto;
import com.bosch.example.dto.JwtPayloadDto;
import com.bosch.example.dto.LoginDto;
import com.bosch.example.dto.TokenDto;
import com.bosch.example.dto.UserDto;
import com.bosch.example.model.UserData;
import com.bosch.example.services.HashSaltingService;
import com.bosch.example.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BcryptController {
    @Autowired
    UserService service;

    @Autowired
    HashSaltingService hashService;

    private Algorithm algorithm = Algorithm.HMAC256("chaveAleatoria");

    @PostMapping("user")
    public String postMethodName(@RequestBody UserDto param) {

        Matcher regexEmail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$").matcher(param.email());

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

        String salt = hashService.SaltHash();

        String password = hashService.HashPassword(param.password(), salt);

        service.save(new UserData(param.username(), password, param.email()));
        return "Salvo com sucesso!";
    }

    @PostMapping("login")
    public AuthDto postMethodName(@RequestBody LoginDto param) {

        UserData user = null;

        if (service.findEmail(param.login()).size() != 0) {
            user = service.findEmail(param.login()).get(0);
        }

        else if (service.findName(param.login()).size() != 0) {
            user = service.findName(param.login()).get(0);
        }

        if (user == null) {
            return new AuthDto("Usuário inválido!", null);
        }

        String pass = user.getPassword().substring(0, user.getPassword().indexOf('$'));
        String salt = user.getPassword().substring(user.getPassword().indexOf('$') + 1, user.getPassword().length());

        String hashPassword = hashService.HashPassword(pass, salt);

        if (param.password().equals(hashPassword)) {
            return new AuthDto("Senha incorreta!", null);
        }

        try {
            String jwtToken = JWT.create()
                .withIssuer("etsDta1")
                .withClaim("id", user.getId())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5000L))
                .sign(algorithm);
                
            return new AuthDto("Login efetuado!", jwtToken);
        } catch(Exception e) {
            return new AuthDto("Algo deu errado!", null);
        } 
    }

    @PostMapping("/product")
    public String postMethodName(@RequestBody TokenDto param) {

        DecodedJWT decodedJWT;

        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("etsDta1").build();
            decodedJWT = verifier.verify(param.token());

        } catch (JWTVerificationException e) {
            System.out.println("Inválido");
        }
        
        String[] chuncks = param.token().split("\\.");

        Claim id = decodedJWT.getClaim("id");

        return id;
    }
}
