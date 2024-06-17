package com.bosch.example.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.bosch.example.services.HashSaltingService;

public class BcryptService implements HashSaltingService {
    @Override
    public String HashPassword(String password, String salt) {
        
        String hashedPassword = password + salt;

        try {
            MessageDigest passDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = passDigest.digest(hashedPassword.getBytes(StandardCharsets.UTF_8));

            hashedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O hash deu errado!");
        }

        return hashedPassword + "$" + salt;
    }

    @Override
    public String SaltHash() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        try {
            MessageDigest saltDigest = MessageDigest.getInstance("SHA-256");
            saltDigest.update(salt);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O salt deu errado!");
        }

        String StringSalt = Base64.getEncoder().encodeToString(salt);

        return StringSalt;
    }
}
