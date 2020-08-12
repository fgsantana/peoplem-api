package com.github.fgsantana.peoplemapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {
    private String message;
}
