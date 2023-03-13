package com.azerty.quizgame.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_question")
    private Long id;

    @Column(nullable = false)
    private String wording;

    @Column(nullable = false)
    private int maxDurationInSeconds;

    @ManyToOne
    @JoinColumn(name = "fk_quiz", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Answer> answers;


    public Question() {
    }

    public Question(Long id, String wording, int maxDurationInSeconds, Quiz quiz, List<Answer> answers) {
        this.wording = wording;
        this.maxDurationInSeconds = maxDurationInSeconds;
        this.quiz = quiz;
        this.answers = new ArrayList<>();
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

    public int getMaxDurationInSeconds() {
        return maxDurationInSeconds;
    }

    public void setMaxDurationInSeconds(int maxDurationInSeconds) {
        this.maxDurationInSeconds = maxDurationInSeconds;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", wording='" + wording + '\'' +
                ", maxDurationInSeconds=" + maxDurationInSeconds +
                ", quiz=" + quiz +
                ", answers=" + answers +
                '}';
    }

}
