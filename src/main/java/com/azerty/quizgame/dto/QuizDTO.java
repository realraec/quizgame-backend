package com.azerty.quizgame.dto;

public class QuizDTO {

    private Long id;
    private String title;
    private String summary;
    private Long[] questionsIds;


    public QuizDTO(Long id, String title, String summary, Long[] questionsIds) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.questionsIds = questionsIds;
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

    public Long[] getQuestionsIds() {
        return questionsIds;
    }

    public void setQuestionsIds(Long[] questionsIds) {
        this.questionsIds = questionsIds;
    }

}
