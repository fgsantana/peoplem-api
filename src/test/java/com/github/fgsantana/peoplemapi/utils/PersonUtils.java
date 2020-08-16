package com.github.fgsantana.peoplemapi.utils;

import com.github.fgsantana.peoplemapi.dto.PersonDTO;
import com.github.fgsantana.peoplemapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class   PersonUtils {
    private static final long ID = 1L;
    private static final String NAME = "Felipe";
    private static final String CPF = "52735519007";
    public static final LocalDate BIRTH_DATE = LocalDate.of(1990, 10, 1);

    public static PersonDTO createFakeDTO(){
        return PersonDTO.builder().name(NAME).cpf(CPF).birthDate("1990-10-01").phones(Collections.singletonList(PhoneUtils.createFakeDTO())).build();
    }
    public static Person createFakeEntity(){
        return Person.builder().id(ID).name(NAME).cpf(CPF).birthDate(BIRTH_DATE).phones(Collections.singletonList(PhoneUtils.createFakeEntity())).build();
    }

}
