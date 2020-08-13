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
import java.util.stream.Collectors;

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
                    .message("Person with ID " + repo.save(person).getId()+ " created").build()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(message.builder().message("Usuário com CPF " + person.getCpf() + " já existente").build());

        }


    }

    public ResponseEntity<List<PersonDTO>> getAll() {
        return ResponseEntity.ok().body(repo.findAll().stream().map(person -> personMapper.toDTO(person)).collect(Collectors.toList()));
    }

    public ResponseEntity<Person> getOne(Long id) {
        Optional<Person> op = repo.findById(id);
        return op.isPresent() ? ResponseEntity.ok().body(op.get()) : ResponseEntity.notFound().build();


    }
}
