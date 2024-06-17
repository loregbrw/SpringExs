package com.bosch.example.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bosch.example.dto.CollatzOutputDto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class CollatzController {
    @GetMapping("collatz")
    public CollatzOutputDto getMethodName(Integer current, Integer step) {
        
        if (current < 0 || step < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Input inválido!!");
        }

        int num = current;

        for (int i = 0; i < step; i++) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = num * 3 + 1;
            }
        }

        return new CollatzOutputDto(num);
    }
    
}
