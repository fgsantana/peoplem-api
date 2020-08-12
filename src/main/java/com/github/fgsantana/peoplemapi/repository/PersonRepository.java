package com.github.fgsantana.peoplemapi.repository;

import com.github.fgsantana.peoplemapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
