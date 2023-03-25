package com.azerty.quizgame.model.entity;

import com.azerty.quizgame.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_person")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String firstname;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Role role;

    private String company;

    @ManyToMany(mappedBy = "persons", fetch = FetchType.EAGER)
    private List<Quiz> quizzes;


    public Person() {
    }

    public Person(Long id, String username, String password, String lastname, String firstname, String email, String company, Role role, List<Quiz> quizzes) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.company = company;
        this.role = role;
        this.quizzes = quizzes;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long pk_id) {
        this.id = pk_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String nom) {
        this.lastname = nom;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String prenom) {
        this.firstname = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String telephone) {
        this.email = telephone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", company='" + company + '\'' +
                ", quizzes=" + quizzes +
                '}';
    }

}
