package com.azerty.quizgame.controller;

import com.azerty.quizgame.model.dto.RecordDTO;
import com.azerty.quizgame.model.entity.Record;
import com.azerty.quizgame.service.ProgressService;
import com.azerty.quizgame.service.RecordService;
import com.azerty.quizgame.utils.RecordMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/records")
@CrossOrigin("*")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private ProgressService progressService;

    private final RecordMapper recordMapper = new RecordMapper();

    @GetMapping
    public ResponseEntity<List<RecordDTO>> getAllRecords() {
        try {
            List<RecordDTO> records = recordService.getAllRecords();
            return new ResponseEntity<>(records, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RecordDTO> getRecordById(@PathVariable Long id) {
        try {
            RecordDTO record = recordService.getRecordById(id);
            if (record != null) {
                return new ResponseEntity<>(record, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @PostMapping(path = "/create")
    public ResponseEntity<RecordDTO> saveRecord(@RequestBody RecordDTO record) {
        try {
            Record recordAsEntity = recordMapper.toRecord(record);
            Long progressId = recordAsEntity.getProgress().getId();
            Long questionId = recordAsEntity.getQuestion().getId();
            RecordDTO checkRecord = recordService.getRecordByProgressIdAndQuestionId(progressId, questionId);

            if (checkRecord == null) {
                RecordDTO savedRecord = recordService.saveRecord(record);
                progressService.updateProgressDependingOnRecord(record);
                return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(checkRecord, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RecordDTO> updateRecordById(@RequestBody RecordDTO record, @PathVariable Long id) {
        try {
            RecordDTO updatedRecord = recordService.updateRecordById(record, id);
            if (updatedRecord != null) {
                return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deleteRecordById(@PathVariable Long id) {
        try {
            boolean deletedOrNot = recordService.deleteRecordById(id);
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
