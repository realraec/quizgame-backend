package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.dto.QuestionInQuizDTO;
import com.azerty.quizgame.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/questions")
@CrossOrigin("*")
public class QuestionController {

    private final QuestionService questionService;


    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        try {
            List<QuestionDTO> questions = questionService.getAllQuestions();
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) {
        try {
            QuestionDTO question = questionService.getQuestionById(id);
            if (question != null) {
                return new ResponseEntity<>(question, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<QuestionDTO> saveQuestion(@RequestBody QuestionDTO question) {
        try {
            return new ResponseEntity<>(questionService.saveQuestion(question), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<QuestionDTO> updateQuestionById(@RequestBody QuestionDTO question, @PathVariable Long id) {
        try {
            QuestionDTO updatedQuestion = questionService.updateQuestionById(question, id);
            if (updatedQuestion != null) {
                return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteQuestionById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = questionService.deleteQuestionById(id);
            if (deletedOrNot) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/quiz/{quizId}")
    public ResponseEntity<List<QuestionDTO>> getAllQuestionsByQuizId(@PathVariable Long quizId) {
        try {
            List<QuestionDTO> questions = questionService.getAllQuestionsByQuizId(quizId);
            if (questions != null) {
                return new ResponseEntity<>(questions, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/notInProgress/{progressId}")
    public ResponseEntity<QuestionInQuizDTO> getOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId(@PathVariable Long progressId) {
        try {
            QuestionInQuizDTO questionInQuizDTO = questionService.getOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId(progressId);
            if (questionInQuizDTO != null) {
                if (questionInQuizDTO.getId() != null) {
                    return new ResponseEntity<>(questionInQuizDTO, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
