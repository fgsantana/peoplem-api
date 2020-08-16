package com.github.fgsantana.peoplemapi.service;


import com.github.fgsantana.peoplemapi.dto.PersonDTO;
import com.github.fgsantana.peoplemapi.dto.ResponseMessage;
import com.github.fgsantana.peoplemapi.entity.Person;
import com.github.fgsantana.peoplemapi.exception.CPFConstraintViolationException;
import com.github.fgsantana.peoplemapi.repository.PersonRepository;
import com.github.fgsantana.peoplemapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.github.fgsantana.peoplemapi.utils.PersonUtils.createFakeDTO;
import static com.github.fgsantana.peoplemapi.utils.PersonUtils.createFakeEntity;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    PersonRepository repo;

    @InjectMocks
    PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() throws CPFConstraintViolationException {
        PersonDTO personDTO = createFakeDTO();
        Person expctdSavedPerson = createFakeEntity();

        Mockito.when(repo.save(expctdSavedPerson)).thenReturn(expctdSavedPerson);

        ResponseMessage expectedSuccessMessage = ResponseMessage.builder().message("Person with ID " + repo.save(expctdSavedPerson).getId() + " created").build();
        ResponseMessage succesMessage = personService.createPerson(personDTO);


    }
}
