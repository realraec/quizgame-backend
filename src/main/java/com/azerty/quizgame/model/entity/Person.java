package com.azerty.quizgame.model.entity;

import com.azerty.quizgame.model.enums.Role;
import jakarta.persistence.*;

@Access(AccessType.FIELD)
@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_person")
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String username;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected String lastname;

    @Column(nullable = false)
    protected String firstname;

    @Column(nullable = false, unique = true)
    protected String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;


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

}
