package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.InternDTO;
import com.azerty.quizgame.model.entity.Intern;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class InternMapperTests {

    private final InternMapper internMapper = new InternMapper();

    @Test
    public void shouldGoFromInternToInternDTO() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new Quiz());
        quizzes.add(new Quiz());
        //Long[] quizzesIds = quizzes.stream().map(Quiz::getId).toArray(Long[]::new);

        Intern admin = new Intern(id, username, password, lastname, firstname, email, company, role, null);
        admin.setQuizzes(quizzes);
        InternDTO internDTO = internMapper.toInternDTO(admin);

        Assertions.assertEquals(null, internDTO.getId());
        Assertions.assertEquals(username, internDTO.getUsername());
        Assertions.assertEquals(password, internDTO.getPassword());
        Assertions.assertEquals(lastname, internDTO.getLastname());
        Assertions.assertEquals(firstname, internDTO.getFirstname());
        Assertions.assertEquals(email, internDTO.getEmail());
        Assertions.assertEquals(company, internDTO.getCompany());
        Assertions.assertEquals(role, internDTO.getRole());
        Assertions.assertEquals(quizzes.size(), internDTO.getQuizzesIds().length);
    }

    @Test
    public void shouldGoFromInternDTOToIntern() {
        Long id = 1L;
        String username = "user1";
        String password = "P@ssword1";
        String lastname = "Tyson";
        String firstname = "Mike";
        String email = "mike.tyson@gmail.com";
        String company = "McDonald's";
        Role role = Role.INTERN;
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new Quiz());
        quizzes.add(new Quiz());
        Long[] quizzesIds = quizzes.stream().map(Quiz::getId).toArray(Long[]::new);

        InternDTO internDTO = new InternDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);
        Intern intern = internMapper.toIntern(internDTO);

        Assertions.assertEquals(null, intern.getId());
        Assertions.assertEquals(username, intern.getUsername());
        Assertions.assertEquals(password, intern.getPassword());
        Assertions.assertEquals(lastname, intern.getLastname());
        Assertions.assertEquals(firstname, intern.getFirstname());
        Assertions.assertEquals(email, intern.getEmail());
        Assertions.assertEquals(company, intern.getCompany());
        Assertions.assertEquals(role, intern.getRole());
        Assertions.assertEquals(0, intern.getQuizzes().size());
    }

}
