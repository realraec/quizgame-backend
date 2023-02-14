package com.azerty.quizgame.dto;

import com.azerty.quizgame.model.Role;

public class AdminDTO {

    private Long id;
    private String username;
    private String password;
    private String lastname;
    private String firstname;
    private String email;
    private Role role;


    public AdminDTO(Long id, String username, String password, String lastname, String firstname, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
