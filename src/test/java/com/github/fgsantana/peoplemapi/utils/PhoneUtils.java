package com.github.fgsantana.peoplemapi.utils;

import com.github.fgsantana.peoplemapi.dto.PhoneDTO;
import com.github.fgsantana.peoplemapi.entity.Phone;
import com.github.fgsantana.peoplemapi.entity.PhoneType;

public class PhoneUtils {
    private static final String number = "1234567891239";
    private static final PhoneType type = PhoneType.MOBILE;
    private static final long id = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(number)
                .type(type)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(id)
                .number(number)
                .type(type)
                .build();
    }
}
