package com.azerty.quizgame.model.dto;

import java.time.LocalDateTime;

public class ProgressDTO {

    private Long id;
    private LocalDateTime dateAndTimeOfCompletion;
    private int score;
    private Long internId;
    private Long quizId;
    private Long[] recordsIds;


    public ProgressDTO() {
    }

    public ProgressDTO(Long id, LocalDateTime dateAndTimeOfCompletion, int score, Long internId, Long quizId, Long[] recordsIds) {
        this.id = id;
        this.dateAndTimeOfCompletion = dateAndTimeOfCompletion;
        this.score = score;
        this.internId = internId;
        this.quizId = quizId;
        this.recordsIds = recordsIds;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTimeOfCompletion() {
        return dateAndTimeOfCompletion;
    }

    public void setDateAndTimeOfCompletion(LocalDateTime dateAndTimeOfCompletion) {
        this.dateAndTimeOfCompletion = dateAndTimeOfCompletion;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getInternId() {
        return internId;
    }

    public void setInternId(Long internId) {
        this.internId = internId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long[] getRecordsIds() {
        return recordsIds;
    }

    public void setRecordsIds(Long[] recordsIds) {
        this.recordsIds = recordsIds;
    }

}
