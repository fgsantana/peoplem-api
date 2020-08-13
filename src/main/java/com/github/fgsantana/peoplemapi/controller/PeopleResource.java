package com.github.fgsantana.peoplemapi.controller;

import com.github.fgsantana.peoplemapi.dto.PersonDTO;
import com.github.fgsantana.peoplemapi.dto.ResponseMessage;
import com.github.fgsantana.peoplemapi.entity.Person;
import com.github.fgsantana.peoplemapi.repository.PersonRepository;
import com.github.fgsantana.peoplemapi.service.PersonService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/people")
public class PeopleResource {

    @Autowired
    PersonService service;


    @GetMapping
    public ResponseEntity<List<PersonDTO>> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") Long id) {
        return service.getOne(id);

    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return service.createPerson(personDTO);

    }

}
