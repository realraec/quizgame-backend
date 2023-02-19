package com.azerty.quizgame.controller;

import com.azerty.quizgame.dto.QuestionDTO;
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

    @Autowired
    private QuestionService questionService;

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
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/quiz/{quizId}/questions/{questionsIds}")
    public ResponseEntity<List<QuestionDTO>> getAllQuestionsByQuizIdWithIdNotInArray(@PathVariable Long quizId, @PathVariable Long[] questionsIds) {
        try {
            List<QuestionDTO> questions = questionService.getAllQuestionsByQuizIdWithIdNotInArray(quizId, questionsIds);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/intern/{internId}/quiz/{quizId}")
    public ResponseEntity<List<QuestionDTO>> getAllQuestionsByInternIdAndQuizId(@PathVariable Long internId, @PathVariable Long quizId) {
        try {
            List<QuestionDTO> questions = questionService.getAllQuestionsByInternIdAndQuizId(internId, quizId);
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/notInProgress/{progressId}")
    public ResponseEntity<QuestionDTO> getSingleQuestionInQuizWithIdNotInProgressRecordsByProgressId(@PathVariable Long progressId) {
        try {
            QuestionDTO question = questionService.getSingleQuestionInQuizWithIdNotInProgressRecordsByProgressId(progressId);
            if (question != null) {
                return new ResponseEntity<>(question, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
