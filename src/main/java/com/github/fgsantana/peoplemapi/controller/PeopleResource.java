package com.github.fgsantana.peoplemapi.controller;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PeopleResource {

    @GetMapping
    public String test(){
        return "Hello, world!";
    }
}
