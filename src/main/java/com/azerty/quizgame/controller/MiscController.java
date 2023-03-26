package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.CountsDTO;
import com.azerty.quizgame.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/misc")
@CrossOrigin("*")
public class MiscController {

    private final MiscService miscService;


    @Autowired
    public MiscController(MiscService miscService) {
        this.miscService = miscService;
    }


    @GetMapping(path = "/counts")
    public ResponseEntity<CountsDTO> getInternCountAndQuizCount() {
        try {
            CountsDTO counts = miscService.getInternCountAndQuizCount();
            return new ResponseEntity<>(counts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/clear")
    public Object clearDatabase() {
        try {
            miscService.clearDatabase();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/reset")
    public Object resetDatabase() {
        try {
            miscService.resetDatabase();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
