package com.bosch.example.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.bosch.example.dto.ImaginaryDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ImaginaryController {

    @GetMapping("imaexp")
    public ImaginaryDto getMethodName(@RequestParam double A, @RequestParam double b) {

        double Re = A * Math.sin(b);
        double Im = A * Math.cos(b);

        return new ImaginaryDto(Re, Im);
    }
    
}