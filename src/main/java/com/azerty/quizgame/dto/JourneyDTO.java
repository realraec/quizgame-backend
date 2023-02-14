package com.azerty.quizgame.dto;

import java.time.LocalDateTime;

public class JourneyDTO {

    private Long id;
    private LocalDateTime dateAndTimeOfCompletion;
    private int durationInSeconds;
    private int score;
    private Long internId;
    private Long quizId;
    private Long[] answersIds;


    public JourneyDTO(Long id, LocalDateTime dateAndTimeOfCompletion, int durationInSeconds, int score, Long internId, Long quizId, Long[] answersIds) {
        this.id = id;
        this.dateAndTimeOfCompletion = dateAndTimeOfCompletion;
        this.durationInSeconds = durationInSeconds;
        this.score = score;
        this.internId = internId;
        this.quizId = quizId;
        this.answersIds = answersIds;
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

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
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

    public Long[] getAnswersIds() {
        return answersIds;
    }

    public void setAnswersIds(Long[] answersIds) {
        this.answersIds = answersIds;
    }

}
