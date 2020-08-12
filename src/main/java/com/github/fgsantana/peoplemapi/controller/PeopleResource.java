package com.github.fgsantana.peoplemapi.controller;

import com.github.fgsantana.peoplemapi.entity.Person;
import com.github.fgsantana.peoplemapi.repository.PersonRepository;
import com.github.fgsantana.peoplemapi.service.PersonService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/people")
public class PeopleResource {

    @Autowired
    PersonService service;


    @GetMapping
    public ResponseEntity all(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        return service.getOne(id);

    }

    @PostMapping
    public ResponseEntity createPerson(@RequestBody Person person){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPerson(person));

    }

}
