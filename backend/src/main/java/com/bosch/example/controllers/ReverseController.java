package com.bosch.example.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.bosch.example.dto.ReverseDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ReverseController {
    
    @GetMapping("reverse/{text}")
    public ReverseDto getMethodName(@PathVariable String text) {

        String reversedText = "";

        for (int i = 0; i < text.length(); i++) {
            reversedText += text.charAt(text.length() - i - 1);
        }

        return new ReverseDto(reversedText, reversedText.equals(text));
    }
    
}