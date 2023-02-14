package com.azerty.quizgame.utils;

import com.azerty.quizgame.dto.InternDTO;
import com.azerty.quizgame.model.Intern;
import com.azerty.quizgame.model.Role;
import org.springframework.stereotype.Component;

@Component
public class InternMapper {

    public InternDTO toInternDTO(Intern intern) {
        Long id = intern.getId();
        String username = intern.getUsername();
        String password = intern.getPassword();
        String lastname = intern.getLastname();
        String firstname = intern.getFirstname();
        String email = intern.getEmail();
        String company = intern.getCompany();
        Role role = intern.getRole();

        return new InternDTO(id, username, password, lastname, firstname, email, company, role);
    }

    public Intern toIntern(InternDTO internDTO) {
        return new Intern(internDTO.getId(), internDTO.getUsername(), internDTO.getPassword(), internDTO.getLastname(), internDTO.getFirstname(), internDTO.getEmail(), internDTO.getCompany(), internDTO.getRole());
    }

}
