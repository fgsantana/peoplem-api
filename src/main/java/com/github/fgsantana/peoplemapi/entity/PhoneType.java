package com.github.fgsantana.peoplemapi.entity;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public enum PhoneType {
    HOME("Home"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private String description;
}
