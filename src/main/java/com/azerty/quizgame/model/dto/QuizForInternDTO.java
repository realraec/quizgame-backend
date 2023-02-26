package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.entity.QuizState;

public class QuizForInternDTO {

    private Long id;
    private String title;
    private String summary;
    private QuizState state;


    public QuizForInternDTO() {
    }

    public QuizForInternDTO(Long id, String title, String summary, QuizState state) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.state = state;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public QuizState getState() {
        return state;
    }

    public void setState(QuizState state) {
        this.state = state;
    }

}
