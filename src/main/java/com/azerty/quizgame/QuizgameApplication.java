package com.azerty.quizgame;

import com.azerty.quizgame.dao.*;
import com.azerty.quizgame.model.*;
import com.azerty.quizgame.model.Record;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class QuizgameApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizgameApplication.class, args);
    }

    /*@Bean
    CommandLineRunner start(AdminDAO adminDAO, InternDAO internDAO, QuizDAO quizDAO, QuestionDAO questionDAO, AnswerDAO answerDAO, ProgressDAO progressDAO, RecordDAO recordDAO) {
        return args -> {

            Admin admin1 = new Admin(null, "superadmin", "password123", "BOULBI", "Boulga", "bb@bb.com", null);
            Admin admin2 = new Admin(null, "wowowowow", "6675455645645", "SURPRISE", "Surprise", "wowowow@google.com", null);
            Admin admin3 = new Admin(null, "admin3", "*$⁾=$ù)=)$⁼", "DOE", "John", "john.doe@mail.com", null);
            adminDAO.save(admin1);
            adminDAO.save(admin2);
            adminDAO.save(admin3);
            adminDAO.findAll().forEach(System.out::println);

            Intern intern1 = new Intern(null, "p.candy", "dfdloihgfd", "CANDY", "Philip", "philip.candy@gmail.com", "Google", null);
            Intern intern2 = new Intern(null, "m.snack", "795678537564", "SNACK", "Mary", "mmmary.sssnack@hotmail.com", "Amazon", null);
            Intern intern3 = new Intern(null, "j.doe", "zigouigoui", "DOE", "Jane", "jane.doe@mail.com", "FakeCompanyTM", null);
            internDAO.save(intern1);
            internDAO.save(intern2);
            internDAO.save(intern3);
            internDAO.findAll().forEach(System.out::println);

            Quiz quiz1 = new Quiz(null, "Physics", "This test is about physics.", null);
            Quiz quiz2 = new Quiz(null, "Geology", "Rocks! Minerals! Dirt!", null);
            Quiz quiz3 = new Quiz(null, "Java", "All about the Java programming language.", null);
            quizDAO.save(quiz1);
            quizDAO.save(quiz2);
            quizDAO.save(quiz3);
            quizDAO.findAll().forEach(System.out::println);

            Question question1 = new Question(null, "What is the symbol used at the end of a command in Java?", 30, quiz3, null);
            Question question2 = new Question(null, "Is Java an OOP language?", 15, quiz3, null);
            Question question3 = new Question(null, "What does OOP stand for?", 60, quiz3, null);
            Question question4 = new Question(null, "What are all the possible values a boolean can take?", 30, quiz3, null);
            Question question5 = new Question(null, "Are the laws of physics a thing?", 15, quiz1, null);
            questionDAO.save(question1);
            questionDAO.save(question2);
            questionDAO.save(question3);
            questionDAO.save(question4);
            questionDAO.save(question5);
            questionDAO.findAll().forEach(System.out::println);

            Answer answer1a = new Answer(null, ";", true, question1);
            Answer answer1b = new Answer(null, "$", false, question1);
            Answer answer1c = new Answer(null, "*", false, question1);
            Answer answer1d = new Answer(null, "#", false, question1);
            Answer answer2a = new Answer(null, "Yes it is.", true, question2);
            Answer answer2b = new Answer(null, "No it is not.", false, question2);
            Answer answer3a = new Answer(null, "Object-Oriented Programming", true, question3);
            Answer answer3b = new Answer(null, "Obviously Over-the-top Proposition", false, question3);
            Answer answer3c = new Answer(null, "Ohmygod Ohmygod Please", false, question3);
            Answer answer3d = new Answer(null, "Out Of Perception", false, question3);
            Answer answer3e = new Answer(null, "It does not mean anything.", false, question3);
            answerDAO.save(answer1a);
            answerDAO.save(answer1b);
            answerDAO.save(answer1c);
            answerDAO.save(answer1d);
            answerDAO.save(answer2a);
            answerDAO.save(answer2b);
            answerDAO.save(answer3a);
            answerDAO.save(answer3b);
            answerDAO.save(answer3c);
            answerDAO.save(answer3d);
            answerDAO.save(answer3e);

            Record recordQuestion1Intern1 = new Record(null, question1, true);
            Record recordQuestion2Intern1 = new Record(null, question2, false);
            Record recordQuestion3Intern1 = new Record(null, question3, false);
            Record recordQuestion1Intern2 = new Record(null, question1, true);
            Record recordQuestion2Intern2 = new Record(null, question2, true);
            Record recordQuestion1Intern3 = new Record(null, question1, false);
            Record recordQuestion2Intern3 = new Record(null, question2, true);
            Record recordQuestion3Intern3 = new Record(null, question3, true);
            Record recordQuestion4Intern3 = new Record(null, question4, false);
            recordDAO.save(recordQuestion1Intern1);
            recordDAO.save(recordQuestion2Intern1);
            recordDAO.save(recordQuestion3Intern1);
            recordDAO.save(recordQuestion1Intern2);
            recordDAO.save(recordQuestion2Intern2);
            recordDAO.save(recordQuestion1Intern3);
            recordDAO.save(recordQuestion2Intern3);
            recordDAO.save(recordQuestion3Intern3);
            recordDAO.save(recordQuestion4Intern3);

            List<Record> records1Intern1 = new ArrayList<>();
            records1Intern1.add(recordQuestion1Intern1);
            records1Intern1.add(recordQuestion2Intern1);
            records1Intern1.add(recordQuestion3Intern1);
            List<Record> records2Intern1 = new ArrayList<>();
            records2Intern1.add(recordQuestion1Intern2);
            records2Intern1.add(recordQuestion2Intern2);
            List<Record> records1Intern2 = new ArrayList<>();
            records1Intern2.add(recordQuestion1Intern3);
            records1Intern2.add(recordQuestion2Intern3);
            records1Intern2.add(recordQuestion3Intern3);
            records1Intern2.add(recordQuestion4Intern3);
            Progress progress1 = new Progress(null, null, 0, 0, intern1, quiz3, records1Intern1);
            Progress progress2 = new Progress(null, null, 0, 0, intern2, quiz3, records2Intern1);
            Progress progress3 = new Progress(null, null, 0, 0, intern3, quiz3, records1Intern2);
            progressDAO.save(progress1);
            progressDAO.save(progress2);
            progressDAO.save(progress3);

        };
    }*/

}
