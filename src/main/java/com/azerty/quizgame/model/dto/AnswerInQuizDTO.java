package com.azerty.quizgame.model.dto;

public class AnswerInQuizDTO {

    private Long id;
    private boolean isCorrect;
    private String wording;


    public AnswerInQuizDTO() {
    }

    public AnswerInQuizDTO(Long id, boolean isCorrect, String wording) {
        this.id = id;
        this.isCorrect = isCorrect;
        this.wording = wording;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

}
