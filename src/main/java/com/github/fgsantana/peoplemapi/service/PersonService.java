package com.github.fgsantana.peoplemapi.service;


import com.github.fgsantana.peoplemapi.entity.Person;
import com.github.fgsantana.peoplemapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    HashMap<String, String> map = new HashMap<>();

    @Autowired
    PersonRepository repo;

    public HashMap<String, String> createPerson(Person person) {

        map.put("id", repo.save(person).getId().toString());
        return map;

    }

    public List<Person> getAll(){
        return repo.findAll();
    }

    public ResponseEntity getOne(Long id){
        Optional<Person> op= repo.findById(id);
        return op.isPresent() ? ResponseEntity.ok().body(op.get()) : ResponseEntity.notFound().build();


    }
}
