package com.azerty.quizgame.model.dto;

import com.azerty.quizgame.model.enums.Role;

public class InternDTO {

    private Long id;
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String email;
    private String company;
    private Role role;
    private Long[] quizzesIds;

    public InternDTO() {
    }

    public InternDTO(Long id, String username, String password, String lastname, String firstname, String email, String company, Role role, Long[] quizzesIds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.company = company;
        this.role = role;
        this.quizzesIds = quizzesIds;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long[] getQuizzesIds() {
        return quizzesIds;
    }

    public void setQuizzesIds(Long[] quizzesIds) {
        this.quizzesIds = quizzesIds;
    }

}
