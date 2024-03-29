package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/persons")
@CrossOrigin("*")
public class PersonController {

    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonController(PersonService personService, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/admins")
    public ResponseEntity<List<PersonDTO>> getAllAdmins() {
        try {
            List<PersonDTO> admins = personService.getAllAdmins();
            return new ResponseEntity<>(admins, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/interns")
    public ResponseEntity<List<PersonDTO>> getAllInterns() {
        try {
            List<PersonDTO> interns = personService.getAllInterns();
            return new ResponseEntity<>(interns, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        try {
            PersonDTO person = personService.getPersonById(id);
            if (person != null) {
                return new ResponseEntity<>(person, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/username/{username}")
    public ResponseEntity<PersonDTO> getPersonByUsername(@PathVariable String username) {
        try {
            PersonDTO person = personService.getPersonByUsername(username);
            if (person != null) {
                return new ResponseEntity<>(person, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deletePersonById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = personService.deletePersonById(id);
            if (deletedOrNot) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO person) {
        try {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PersonDTO> updatePersonById(@RequestBody PersonDTO person, @PathVariable Long id) {
        try {
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            PersonDTO updatedIntern = personService.updatePersonById(person, id);
            if (updatedIntern != null) {
                return new ResponseEntity<>(updatedIntern, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // CONFLICT / BAD REQUEST
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/attributedToQuiz/{quizId}")
    public ResponseEntity<List<PersonDTO>> getAllPersonsAttributedToQuizByQuizId(@PathVariable Long quizId) {
        try {
            List<PersonDTO> personsAttributed = personService.getAllPersonsAttributedToQuizByQuizId(quizId);
            if (personsAttributed != null) {
                return new ResponseEntity<>(personsAttributed, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/notAttributedToQuiz/{quizId}")
    public ResponseEntity<List<PersonDTO>> getAllPersonsNotAttributedToQuizByQuizId(@PathVariable Long quizId) {
        try {
            List<PersonDTO> personsAttributed = personService.getAllPersonsNotAttributedToQuizByQuizId(quizId);
            if (personsAttributed != null) {
                return new ResponseEntity<>(personsAttributed, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
