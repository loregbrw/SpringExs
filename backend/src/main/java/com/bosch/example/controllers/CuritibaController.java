package com.bosch.example.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bosch.example.dto.CuritibaOutputDto;
import com.bosch.example.dto.ViaCEPData;
import com.bosch.example.services.CPFValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CuritibaController {

    @Autowired
    CPFValidator validator;

    @GetMapping("curitiba")
    public CuritibaOutputDto getMethodName(@RequestParam String cep, String cpf) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://viacep.com.br/ws/" + cep + "/json";
        ViaCEPData result = restTemplate.getForObject(url, ViaCEPData.class);

        boolean isFromCuritiba = result.localidade().equals("Curitiba");
        boolean cpfIsValid = validator.isValid(cpf);

        boolean status = isFromCuritiba && cpfIsValid;
        String msg = new String();

        if (!isFromCuritiba) {
            msg += "CEP não é de Curitiba!\n";
        }
        if (!cpfIsValid) {
            msg += "CPF inválido\n";
        }

        return new CuritibaOutputDto(status, status ? "Sucesso!" : msg);
    }
}

