package com.azerty.quizgame.model.dto;

public class AnswerInQuizDTO {

    private Long id;
    private String wording;
    private boolean isCorrect;


    public AnswerInQuizDTO() {
    }

    public AnswerInQuizDTO(Long id, String wording, boolean isCorrect) {
        this.id = id;
        this.wording = wording;
        this.isCorrect = isCorrect;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

}
