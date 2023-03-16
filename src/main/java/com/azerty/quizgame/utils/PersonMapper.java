package com.azerty.quizgame.utils;

import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.model.entity.Person;
import com.azerty.quizgame.model.entity.Quiz;
import com.azerty.quizgame.model.enums.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper {

    public PersonDTO toPersonDTO(Person person) {
        Long id = person.getId();
        String username = person.getUsername();
        String password = person.getPassword();
        String lastname = person.getLastname();
        String firstname = person.getFirstname();
        String email = person.getEmail();
        String company = person.getCompany();
        Role role = person.getRole();
        List<Quiz> quizzes = person.getQuizzes();
        Long[] quizzesIds = quizzes.stream().map(Quiz::getId).toArray(Long[]::new);

        return new PersonDTO(id, username, password, lastname, firstname, email, company, role, quizzesIds);
    }

    public Person toPerson(PersonDTO personDTO) {
        List<Quiz> quizzes = new ArrayList<>();
        Long[] quizzesIds = personDTO.getQuizzesIds();
        if (quizzesIds != null) {
            for (int i = 0; i < quizzesIds.length; i++) {
                Quiz quiz = new Quiz();
                quiz.setId(quizzesIds[i]);
                quizzes.add(quiz);
            }
        }

        return new Person(
                personDTO.getId(),
                personDTO.getUsername(),
                personDTO.getPassword(),
                personDTO.getLastname(),
                personDTO.getFirstname(),
                personDTO.getEmail(),
                personDTO.getCompany(),
                personDTO.getRole(),
                quizzes);
    }

}
