package com.azerty.quizgame.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_quiz")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Question> questions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "quizzes_interns",
            joinColumns = { @JoinColumn(name = "pk_quiz") },
            inverseJoinColumns = { @JoinColumn(name = "pk_intern") }
    )
    private List<Intern> interns;


    public Quiz() {
        this.questions = new ArrayList<>();
        this.interns = new ArrayList<>();
    }

    public Quiz(Long id, String title, String summary, List<Question> questions, List<Intern> interns) {
        this.title = title;
        this.summary = summary;
        this.questions = new ArrayList<>();
        this.interns = new ArrayList<>();
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Intern> getInterns() {
        return interns;
    }

    public void setInterns(List<Intern> interns) {
        this.interns = interns;
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                //", questions=" + questions +
                '}';
    }

}
