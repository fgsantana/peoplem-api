package com.github.fgsantana.peoplemapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CPFConstraintViolationException extends Exception {

    public CPFConstraintViolationException(String cpf) {
        super("Person with cpf " + cpf + " already created" );
    }


}
