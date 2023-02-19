package com.azerty.quizgame.model;

import jakarta.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_answer")
    private Long id;

    @Column(nullable = false)
    private String wording;

    @Column(nullable = false)
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "fk_question", nullable = false)
    private Question question;


    public Answer() {
    }

    public Answer(Long id, String wording, boolean isCorrect, Question question) {
        this.wording = wording;
        this.isCorrect = isCorrect;
        this.question = question;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", wording='" + wording + '\'' +
                ", isCorrect=" + isCorrect +
                ", question=" + question +
                '}';
    }

}
