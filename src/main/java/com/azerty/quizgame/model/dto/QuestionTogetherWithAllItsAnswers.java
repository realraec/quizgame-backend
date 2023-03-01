package com.azerty.quizgame.model.dto;

public class QuestionTogetherWithAllItsAnswers {

    private Long pk_question;
    private int max_duration_in_seconds;
    private String questionWording;
    private Long pk_answer;
    private boolean is_correct;
    private String answerWording;

    public QuestionTogetherWithAllItsAnswers() {
    }

    public QuestionTogetherWithAllItsAnswers(Long pk_question, int max_duration_in_seconds, String questionWording, Long pk_answer, boolean is_correct, String answerWording) {
        this.pk_question = pk_question;
        this.max_duration_in_seconds = max_duration_in_seconds;
        this.questionWording = questionWording;
        this.pk_answer = pk_answer;
        this.is_correct = is_correct;
        this.answerWording = answerWording;
    }

    public Long getPk_question() {
        return pk_question;
    }

    public void setPk_question(Long pk_question) {
        this.pk_question = pk_question;
    }

    public int getMax_duration_in_seconds() {
        return max_duration_in_seconds;
    }

    public void setMax_duration_in_seconds(int max_duration_in_seconds) {
        this.max_duration_in_seconds = max_duration_in_seconds;
    }

    public String getQuestionWording() {
        return questionWording;
    }

    public void setQuestionWording(String questionWording) {
        this.questionWording = questionWording;
    }

    public Long getPk_answer() {
        return pk_answer;
    }

    public void setPk_answer(Long pk_answer) {
        this.pk_answer = pk_answer;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }

    public String getAnswerWording() {
        return answerWording;
    }

    public void setAnswerWording(String answerWording) {
        this.answerWording = answerWording;
    }

}
