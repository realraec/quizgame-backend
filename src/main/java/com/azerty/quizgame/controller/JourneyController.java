package com.azerty.quizgame.controller;

import com.azerty.quizgame.dto.JourneyDTO;
import com.azerty.quizgame.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/journeys")
public class JourneyController {

    @Autowired
    private JourneyService journeyService;

    @GetMapping
    public ResponseEntity<List<JourneyDTO>> getAllJourneys() {
        try {
            List<JourneyDTO> journeys = journeyService.getAllJourneys();
            return new ResponseEntity<>(journeys, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<JourneyDTO> getJourneyById(@PathVariable Long id) {
        try {
            JourneyDTO journey = journeyService.getJourneyById(id);
            if (journey != null) {
                return new ResponseEntity<>(journey, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<JourneyDTO> saveJourney(@RequestBody JourneyDTO journey) {
        try {
            return new ResponseEntity<>(journeyService.saveJourney(journey), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<JourneyDTO> updateJourneyById(@RequestBody JourneyDTO journey, @PathVariable Long id) {
        try {
            JourneyDTO updatedJourney = journeyService.updateJourneyById(journey, id);
            if (updatedJourney != null) {
                return new ResponseEntity<>(updatedJourney, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteJourneyById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = journeyService.deleteJourneyById(id);
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
