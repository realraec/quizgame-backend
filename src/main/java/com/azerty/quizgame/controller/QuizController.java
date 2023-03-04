package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.QuizDTO;
import com.azerty.quizgame.model.dto.QuizForInternDTO;
import com.azerty.quizgame.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/quizzes")
@CrossOrigin("*")
public class QuizController {

    private final QuizService quizService;


    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @GetMapping
    public ResponseEntity<List<QuizDTO>> getAllQuizzes() {
        try {
            List<QuizDTO> quizzes = quizService.getAllQuizzes();
            return new ResponseEntity<>(quizzes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long id) {
        try {
            QuizDTO quiz = quizService.getQuizById(id);
            if (quiz != null) {
                return new ResponseEntity<>(quiz, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<QuizDTO> saveQuiz(@RequestBody QuizDTO quiz) {
        try {
            return new ResponseEntity<>(quizService.saveQuiz(quiz), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<QuizDTO> updateQuizById(@RequestBody QuizDTO quiz, @PathVariable Long id) {
        try {
            QuizDTO updatedQuiz = quizService.updateQuizById(quiz, id);
            if (updatedQuiz != null) {
                return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteQuizById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = quizService.deleteQuizById(id);
            if (deletedOrNot) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/{quizId}/forIntern/{internId}")
    public ResponseEntity<QuizForInternDTO> getQuizWithStateByQuizIdAndInternId(@PathVariable Long quizId, @PathVariable Long internId) {
        try {
            QuizForInternDTO quiz = quizService.getQuizWithStateByQuizIdAndInternId(quizId, internId);
            if (quiz != null) {
                return new ResponseEntity<>(quiz, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/forIntern/{internId}")
    public ResponseEntity<List<QuizForInternDTO>> getAllQuizzesWithStateByInternId(@PathVariable Long internId) {
        try {
            List<QuizForInternDTO> quizzesForIntern = quizService.getAllQuizzesWithStateByInternId(internId);
            if (quizzesForIntern != null) {
                return new ResponseEntity<>(quizzesForIntern, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
