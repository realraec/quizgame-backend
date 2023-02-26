package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.ProgressDTO;
import com.azerty.quizgame.service.InternService;
import com.azerty.quizgame.service.ProgressService;
import com.azerty.quizgame.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/progresses")
@CrossOrigin("*")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @GetMapping
    public ResponseEntity<List<ProgressDTO>> getAllProgresses() {
        try {
            List<ProgressDTO> progresses = progressService.getAllProgresses();
            return new ResponseEntity<>(progresses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProgressDTO> getProgressById(@PathVariable Long id) {
        try {
            ProgressDTO progress = progressService.getProgressById(id);
            if (progress != null) {
                return new ResponseEntity<>(progress, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ProgressDTO> saveProgress(@RequestBody ProgressDTO progress) {
        try {
            return new ResponseEntity<>(progressService.saveProgress(progress), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ProgressDTO> updateProgressById(@RequestBody ProgressDTO progress, @PathVariable Long id) {
        try {
            ProgressDTO updatedProgress = progressService.updateProgressById(progress, id);
            if (updatedProgress != null) {
                return new ResponseEntity<>(updatedProgress, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteProgressById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = progressService.deleteProgressById(id);
            if (deletedOrNot) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/intern/{id}")
    public ResponseEntity<List<ProgressDTO>> getAllProgressesByInternId(@PathVariable Long id) {
        try {
            List<ProgressDTO> progresses = progressService.getAllProgressesByInternId(id);
            if (progresses != null) {
                return new ResponseEntity<>(progresses, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/intern/{internId}/quiz/{quizId}")
    public ResponseEntity<ProgressDTO> getProgressByInternIdAndQuizId(@PathVariable Long internId, @PathVariable Long quizId) {
        try {
            ProgressDTO progress = progressService.getProgressByInternIdAndQuizId(internId, quizId);
            if (progress != null) {
                return new ResponseEntity<>(progress, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
