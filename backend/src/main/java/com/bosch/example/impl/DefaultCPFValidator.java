package com.bosch.example.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bosch.example.services.CPFValidator;

public class DefaultCPFValidator implements CPFValidator{

    @Override
    public boolean isValid(String cpf) {
        Matcher regexCPF = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}").matcher(cpf);

        if (!regexCPF.matches()) {
            return false;
        }

        cpf = cpf.replaceAll("[^0-9]", "");

        int sumx = 0;
        int sumy = 0;

        boolean flag = false;

        for (int i = 0; i < 10; i++) {
            if (cpf.charAt(i) != cpf.charAt(i + 1)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            return false;
        }

        for (int i = 0; i < 9; i++) {
            sumx += ((int)(cpf.charAt(i) - '0') * (10 - i));
            sumy += ((int)(cpf.charAt(i + 1) - '0') * (10 - i));
        }

        int resultx = sumx % 11 > 1 ? 11 - (sumx % 11) : 0;
        int resulty = sumy % 11 > 1 ? 11 - (sumy % 11) : 0;

        if((int)(cpf.charAt(9) - '0') == resultx && (int)(cpf.charAt(10) - '0') == resulty) {
            return true;
        }

        return false;
    }
    
}
