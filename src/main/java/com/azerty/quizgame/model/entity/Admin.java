package com.azerty.quizgame.model.entity;

import com.azerty.quizgame.model.enums.Role;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "admins")
public class Admin extends Person {

    public Admin() {
        this.role = Role.ADMIN;
    }

    public Admin(Long id, String username, String password, String lastname, String firstname, String email, Role role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.role = Role.ADMIN;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

}
