package com.github.fgsantana.peoplemapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fgsantana.peoplemapi.dto.PersonDTO;
import com.github.fgsantana.peoplemapi.dto.ResponseMessage;
import com.github.fgsantana.peoplemapi.entity.Person;
import com.github.fgsantana.peoplemapi.mapper.PersonMapper;
import com.github.fgsantana.peoplemapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    final PersonMapper personMapper = PersonMapper.INSTANCE;


    @Autowired
    PersonRepository repo;
    SimpleDateFormat a = new SimpleDateFormat("dd-MM-yyyy");


    public ResponseEntity<ResponseMessage> createPerson(PersonDTO personDTO) {
        ResponseMessage message = new ResponseMessage();
        Person person = personMapper.toModel(personDTO);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body((message.builder()
                    .message("Person with ID " + repo.save(person).getId().toString() + " created").build()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(message.builder().message("Usuário com CPF " + person.getCpf() + " já existente").build());

        }


    }

    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(repo.findAll());
    }

    public ResponseEntity getOne(Long id) {
        Optional<Person> op = repo.findById(id);
        return op.isPresent() ? ResponseEntity.ok().body(op.get()) : ResponseEntity.notFound().build();


    }
}
