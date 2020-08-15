package com.github.fgsantana.peoplemapi.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fgsantana.peoplemapi.dto.PersonDTO;
import com.github.fgsantana.peoplemapi.dto.ResponseMessage;
import com.github.fgsantana.peoplemapi.entity.Person;
import com.github.fgsantana.peoplemapi.exception.CPFConstraintViolationException;
import com.github.fgsantana.peoplemapi.exception.PersonNotFoundException;
import com.github.fgsantana.peoplemapi.mapper.PersonMapper;
import com.github.fgsantana.peoplemapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    final PersonMapper personMapper = PersonMapper.INSTANCE;


    @Autowired
    PersonRepository repo;


    public List<PersonDTO> findAll() {
        return repo.findAll().stream().map(person -> personMapper.toDTO(person)).collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = repo.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return personMapper.toDTO(person);


    }

    public ResponseMessage createPerson(PersonDTO personDTO) throws CPFConstraintViolationException {
        Person person = personMapper.toModel(personDTO);
        try {

            return ResponseMessage.builder().message("Person with ID " + repo.save(person).getId() + " created").build();
        } catch (DataIntegrityViolationException e) {
            throw new CPFConstraintViolationException(personDTO.getCpf());
        }
    }



    public PersonDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException, CPFConstraintViolationException {
        verifyIfExists(id);

        try {
            personDTO.setId(id);
            Person person = repo.save(personMapper.toModel(personDTO));
            return personMapper.toDTO(person);
        } catch (DataIntegrityViolationException e) {
            throw new CPFConstraintViolationException(personDTO.getCpf());
        }
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        repo.deleteById(id);

    }

    private void verifyIfExists(Long id) throws PersonNotFoundException {
        if (!(repo.existsById(id))) {
            throw new PersonNotFoundException(id);
        }
    }
}
