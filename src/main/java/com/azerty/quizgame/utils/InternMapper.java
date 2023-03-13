package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.InternDTO;
import com.azerty.quizgame.model.entity.Intern;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        Long[] quizzesIds = intern.getQuizzes().stream().map(Quiz::getId).toArray(Long[]::new);

        return new InternDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);
    }

    public Intern toIntern(InternDTO internDTO) {
        List<Quiz> quizzes = new ArrayList<>();
        Long[] quizzesIds = internDTO.getQuizzesIds();
        for (int i = 0; i < quizzesIds.length; i++) {
            Quiz quiz = new Quiz();
            quiz.setId(quizzesIds[i]);
            quizzes.add(quiz);
        }

        return new Intern(
                internDTO.getId(),
                internDTO.getUsername(),
                internDTO.getPassword(),
                internDTO.getLastname(),
                internDTO.getFirstname(),
                internDTO.getEmail(),
                internDTO.getCompany(),
                internDTO.getRole(),
                quizzes);
    }

}
