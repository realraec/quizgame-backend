package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.AnswerDTO;
import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.service.AnswerService;
import com.azerty.quizgame.service.QuestionService;
import com.azerty.quizgame.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/answers")
@CrossOrigin("*")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

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
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
            QuestionDTO question = questionService.getQuestionById(id);
            if (question == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            List<AnswerDTO> answers = answerService.getAllAnswersByQuestionId(id);
            return new ResponseEntity<>(answers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping(path = "/quiz/{id}")
//    public ResponseEntity<List<AnswerDTO>> getAllAnswersByQuizId(@PathVariable Long id) {
//        try {
//            QuizDTO quiz = quizService.getQuizById(id);
//            if (quiz == null) {
//                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//            }
//            List<AnswerDTO> answers = answerService.getAllAnswersByQuizId(id);
//            return new ResponseEntity<>(answers, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
