package com.azerty.quizgame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "journeys")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_journey")
    private Long id;

    private LocalDateTime dateAndTimeOfCompletion;
    private int durationInSeconds;
    private int score;

    @ManyToOne
    @JoinColumn(name = "fk_intern")
    private Intern intern;

    @ManyToOne
    @JoinColumn(name = "fk_quiz")
    private Quiz quiz;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "fk_answers")
    //, referencedColumnName="DATREG_META_CODE"
    private List<Answer> answers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAndTimeOfCompletion() {
        return dateAndTimeOfCompletion;
    }

    public void setDateAndTimeOfCompletion(LocalDateTime dateAndTime) {
        this.dateAndTimeOfCompletion = dateAndTime;
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

    public Intern getIntern() {
        return intern;
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
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
        return "Journey{" +
                "id=" + id +
                ", dateAndTimeOfCompletion=" + dateAndTimeOfCompletion +
                ", durationInSeconds=" + durationInSeconds +
                ", score=" + score +
                ", intern=" + intern +
                ", quiz=" + quiz +
                ", answers=" + answers +
                '}';
    }

}
