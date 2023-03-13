package com.azerty.quizgame.model.dto;

public class QuizDTO {

    private Long id;
    private String title;
    private String summary;
    private Long[] questionsIds;
    private Long[] internsIds;


    public QuizDTO() {
    }

    public QuizDTO(Long id, String title, String summary, Long[] questionsIds, Long[] internsIds) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.questionsIds = questionsIds;
        this.internsIds = internsIds;
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

    public Long[] getInternsIds() {
        return internsIds;
    }

    public void setInternsIds(Long[] internsIds) {
        this.internsIds = internsIds;
    }

}
