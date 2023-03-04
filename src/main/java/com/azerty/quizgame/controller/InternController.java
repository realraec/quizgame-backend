package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.InternDTO;
import com.azerty.quizgame.service.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/interns")
@CrossOrigin("*")
public class InternController {

    private final InternService internService;


    @Autowired
    public InternController(InternService internService) {
        this.internService = internService;
    }


    @GetMapping
    public ResponseEntity<List<InternDTO>> getAllInterns() {
        try {
            List<InternDTO> interns = internService.getAllInterns();
            return new ResponseEntity<>(interns, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InternDTO> getInternById(@PathVariable Long id) {
        try {
            InternDTO intern = internService.getInternById(id);
            if (intern != null) {
                return new ResponseEntity<>(intern, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<InternDTO> saveIntern(@RequestBody InternDTO intern) {
        try {
            return new ResponseEntity<>(internService.saveIntern(intern), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InternDTO> updateInternById(@RequestBody InternDTO intern, @PathVariable Long id) {
        try {
            InternDTO updatedIntern = internService.updateInternById(intern, id);
            if (updatedIntern != null) {
                return new ResponseEntity<>(updatedIntern, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteInternById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = internService.deleteInternById(id);
            if (deletedOrNot) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
