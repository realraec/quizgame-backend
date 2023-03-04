package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.AnswerDTO;
import com.azerty.quizgame.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/answers")
@CrossOrigin("*")
public class AnswerController {

    private final AnswerService answerService;


    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }


    @GetMapping
    public ResponseEntity<List<AnswerDTO>> getAllAnswers() {
        try {
            List<AnswerDTO> answers = answerService.getAllAnswers();
            return new ResponseEntity<>(answers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable Long id) {
        try {
            AnswerDTO answer = answerService.getAnswerById(id);
            if (answer != null) {
                return new ResponseEntity<>(answer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<AnswerDTO> saveAnswer(@RequestBody AnswerDTO answer) {
        try {
            return new ResponseEntity<>(answerService.saveAnswer(answer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AnswerDTO> updateAnswerById(@RequestBody AnswerDTO answer, @PathVariable Long id) {
        try {
            AnswerDTO updatedAnswer = answerService.updateAnswerById(answer, id);
            if (updatedAnswer != null) {
                return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteAnswerById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = answerService.deleteAnswerById(id);
            if (deletedOrNot) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/question/{id}")
    public ResponseEntity<List<AnswerDTO>> getAllAnswersByQuestionId(@PathVariable Long id) {
        try {
            List<AnswerDTO> answers = answerService.getAllAnswersByQuestionId(id);
            if (answers != null) {
                return new ResponseEntity<>(answers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
