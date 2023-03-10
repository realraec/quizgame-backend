package com.azerty.quizgame.controller;

import com.azerty.quizgame.dao.AnswerDAO;
import com.azerty.quizgame.dao.QuestionDAO;
import com.azerty.quizgame.dao.QuizDAO;
import com.azerty.quizgame.model.dto.AnswerInQuizDTO;
import com.azerty.quizgame.model.dto.QuestionDTO;
import com.azerty.quizgame.model.dto.QuestionInQuizDTO;
import com.azerty.quizgame.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = QuestionController.class)
public class QuestionControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private QuestionDAO questionDAO;
    @MockBean
    private QuizDAO quizDAO;
    @MockBean
    private AnswerDAO answerDAO;


    @Test
    public void shouldGetAllQuestions() throws Exception {
        QuestionDTO question1 = new QuestionDTO();
        QuestionDTO question2 = new QuestionDTO();
        ArrayList<QuestionDTO> questions = new ArrayList<>(List.of(question1, question2));

        given(questionService.getAllQuestions()).willReturn(questions);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(questions.size())));
    }

    @Test
    public void shouldNotGetAllQuestions500() throws Exception {
        given(questionService.getAllQuestions()).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetQuestionById() throws Exception {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long quizId = 2L;
        Long[] answersIds = {3L, 4L, 5L};
        QuestionDTO question = new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);

        given(questionService.getQuestionById(id)).willReturn(question);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(question.getId().toString()))))
                .andExpect(jsonPath("$.wording", is(question.getWording())))
                .andExpect(jsonPath("$.maxDurationInSeconds", is(question.getMaxDurationInSeconds())))
                .andExpect(jsonPath("$.quizId", is(Integer.valueOf(question.getQuizId().toString()))))
                //.andExpect(jsonPath("$.answersIds", is(Arrays.toString(question.getAnswersIds()))))
                .andExpect(jsonPath("$.answersIds[0]", is(Integer.valueOf(question.getAnswersIds()[0].toString()))))
                .andExpect(jsonPath("$.answersIds[1]", is(Integer.valueOf(question.getAnswersIds()[1].toString()))))
                .andExpect(jsonPath("$.answersIds[2]", is(Integer.valueOf(question.getAnswersIds()[2].toString()))));
    }

    @Test
    public void shouldNotGetQuestionById404() throws Exception {
        Long id = 1L;
        given(questionService.getQuestionById(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetQuestionById500() throws Exception {
        Long id = 1L;
        given(questionService.getQuestionById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldSaveQuestion() throws Exception {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long quizId = 2L;
        Long[] answersIds = {3L, 4L, 5L};
        QuestionDTO question = new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);

        given(questionService.saveQuestion(any())).willReturn(question);

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/questions/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(question)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(question.getId().toString()))))
                .andExpect(jsonPath("$.wording", is(question.getWording())))
                .andExpect(jsonPath("$.maxDurationInSeconds", is(question.getMaxDurationInSeconds())))
                .andExpect(jsonPath("$.quizId", is(Integer.valueOf(question.getQuizId().toString()))))
                //.andExpect(jsonPath("$.answersIds", is(Arrays.toString(question.getAnswersIds()))))
                .andExpect(jsonPath("$.answersIds[0]", is(Integer.valueOf(question.getAnswersIds()[0].toString()))))
                .andExpect(jsonPath("$.answersIds[1]", is(Integer.valueOf(question.getAnswersIds()[1].toString()))))
                .andExpect(jsonPath("$.answersIds[2]", is(Integer.valueOf(question.getAnswersIds()[2].toString()))));
    }

    @Test
    public void shouldNotSaveQuestion400() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/questions/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotSaveQuestion404Quiz() throws Exception {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long quizId = 2L;
        Long[] answersIds = {3L, 4L, 5L};
        QuestionDTO question = new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);

        given(quizDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/questions/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(question)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveQuestion404Answer() throws Exception {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long quizId = 2L;
        Long[] answersIds = {3L, 4L, 5L};
        QuestionDTO question = new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);

        given(answerDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/questions/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(question)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotSaveQuestion500() throws Exception {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long quizId = 2L;
        Long[] answersIds = {3L, 4L, 5L};
        QuestionDTO question = new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);

        given(questionService.saveQuestion(any())).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/questions/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(question)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldUpdateQuestionById() throws Exception {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long quizId = 2L;
        Long[] answersIds = {3L, 4L, 5L};
        QuestionDTO question = new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);

        given(questionService.updateQuestionById(any(), eq(id))).willReturn(question);

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/questions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(question)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(question.getId().toString()))))
                .andExpect(jsonPath("$.wording", is(question.getWording())))
                .andExpect(jsonPath("$.maxDurationInSeconds", is(question.getMaxDurationInSeconds())))
                .andExpect(jsonPath("$.quizId", is(Integer.valueOf(question.getQuizId().toString()))))
                //.andExpect(jsonPath("$.answersIds", is(Arrays.toString(question.getAnswersIds()))))
                .andExpect(jsonPath("$.answersIds[0]", is(Integer.valueOf(question.getAnswersIds()[0].toString()))))
                .andExpect(jsonPath("$.answersIds[1]", is(Integer.valueOf(question.getAnswersIds()[1].toString()))))
                .andExpect(jsonPath("$.answersIds[2]", is(Integer.valueOf(question.getAnswersIds()[2].toString()))));
    }

    @Test
    public void shouldNotUpdateQuestionById400() throws Exception {
        Long id = 1L;
        mvc.perform(MockMvcRequestBuilders
                        .put("/api/questions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotUpdateQuestionById404() throws Exception {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long quizId = 2L;
        Long[] answersIds = {3L, 4L, 5L};
        QuestionDTO question = new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);

        given(questionDAO.findById(any())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/questions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(question)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotUpdateQuestionById500() throws Exception {
        Long id = 1L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long quizId = 2L;
        Long[] answersIds = {3L, 4L, 5L};
        QuestionDTO question = new QuestionDTO(id, wording, maxDurationInSeconds, quizId, answersIds);

        given(questionService.updateQuestionById(any(), eq(id))).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/questions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(question)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldDeleteQuestionById() throws Exception {
        Long id = 1L;
        given(questionService.deleteQuestionById(id)).willReturn(true);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/questions/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldNotDeleteQuestionById404() throws Exception {
        Long id = 1L;
        given(questionService.deleteQuestionById(id)).willReturn(false);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/questions/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotDeleteQuestionById500() throws Exception {
        Long id = 1L;
        given(questionService.deleteQuestionById(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/questions/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void shouldGetAllQuestionsByQuizId() throws Exception {
        Long quizId = 1L;

        Long id1 = 2L;
        String wording1 = "What is the meaning of life?";
        int maxDurationInSeconds1 = 30;
        Long quizId1 = 3L;
        Long[] answersIds1 = {4L, 5L, 6L};
        QuestionDTO question1 = new QuestionDTO(id1, wording1, maxDurationInSeconds1, quizId1, answersIds1);
        Long id2 = 7L;
        String wording2 = "What is the meaning of life?";
        int maxDurationInSeconds2 = 30;
        Long quizId2 = 3L;
        Long[] answersIds2 = {8L, 9L, 10L};
        QuestionDTO question2 = new QuestionDTO(id2, wording2, maxDurationInSeconds2, quizId2, answersIds2);
        List<QuestionDTO> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        given(questionService.getAllQuestionsByQuizId(quizId)).willReturn(questions);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/quiz/{id}", quizId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(questions.size())))
                .andExpect(jsonPath("$[0].id", is(Integer.valueOf(question1.getId().toString()))))
                .andExpect(jsonPath("$[0].maxDurationInSeconds", is(question1.getMaxDurationInSeconds())))
                .andExpect(jsonPath("$[0].quizId", is(Integer.valueOf(question1.getQuizId().toString()))))
                .andExpect(jsonPath("$[0].maxDurationInSeconds", is(question1.getMaxDurationInSeconds())))
                .andExpect(jsonPath("$[0].answersIds[0]", is(Integer.valueOf(question1.getAnswersIds()[0].toString()))))
                .andExpect(jsonPath("$[0].answersIds[1]", is(Integer.valueOf(question1.getAnswersIds()[1].toString()))))
                .andExpect(jsonPath("$[0].answersIds[2]", is(Integer.valueOf(question1.getAnswersIds()[2].toString()))))
                .andExpect(jsonPath("$[1].id", is(Integer.valueOf(question2.getId().toString()))))
                .andExpect(jsonPath("$[1].maxDurationInSeconds", is(question2.getMaxDurationInSeconds())))
                .andExpect(jsonPath("$[1].quizId", is(Integer.valueOf(question2.getQuizId().toString()))))
                .andExpect(jsonPath("$[1].maxDurationInSeconds", is(question2.getMaxDurationInSeconds())))
                .andExpect(jsonPath("$[1].answersIds[0]", is(Integer.valueOf(question2.getAnswersIds()[0].toString()))))
                .andExpect(jsonPath("$[1].answersIds[1]", is(Integer.valueOf(question2.getAnswersIds()[1].toString()))))
                .andExpect(jsonPath("$[1].answersIds[2]", is(Integer.valueOf(question2.getAnswersIds()[2].toString()))));
    }

    @Test
    public void shouldNotGetAllQuestionsByQuizId404() throws Exception {
        Long id = 1L;
        given(questionService.getAllQuestionsByQuizId(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/quiz/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetAllQuestionsByQuizId500() throws Exception {
        Long id = 1L;
        given(questionService.getAllQuestionsByQuizId(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/quiz/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void shouldGetOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId() throws Exception {
        Long progressId = 1L;

        Long id = 2L;
        String wording = "What is the meaning of life?";
        int maxDurationInSeconds = 30;
        Long numberOfQuestionsLeft = 10L;

        Long answerId1 = 3L;
        boolean answerIsCorrect1 = true;
        String answerWording1 = "The right answer";
        AnswerInQuizDTO answer1 = new AnswerInQuizDTO(answerId1, answerWording1, answerIsCorrect1);
        Long answerId2 = 4L;
        boolean answerIsCorrect2 = true;
        String answerWording2 = "The right answer";
        AnswerInQuizDTO answer2 = new AnswerInQuizDTO(answerId2, answerWording2, answerIsCorrect2);
        AnswerInQuizDTO[] answers = {answer1, answer2};
        QuestionInQuizDTO question = new QuestionInQuizDTO(id, wording, maxDurationInSeconds, answers, numberOfQuestionsLeft);

        given(questionService.getOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId(any())).willReturn(question);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/notInProgress/{id}", progressId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Integer.valueOf(question.getId().toString()))))
                .andExpect(jsonPath("$.maxDurationInSeconds", is(question.getMaxDurationInSeconds())))
                .andExpect(jsonPath("$.wording", is(question.getWording())))
                .andExpect(jsonPath("$.numberOfQuestionsLeft", is(Integer.valueOf(question.getNumberOfQuestionsLeft().toString()))))
                .andExpect(jsonPath("$.answers.size()", is(question.getAnswers().length)))
                .andExpect(jsonPath("$.answers[0].id", is(Integer.valueOf(question.getAnswers()[0].getId().toString()))))
                .andExpect(jsonPath("$.answers[0].correct", is(question.getAnswers()[0].isCorrect())))
                .andExpect(jsonPath("$.answers[0].wording", is(question.getAnswers()[0].getWording())))
                .andExpect(jsonPath("$.answers[1].id", is(Integer.valueOf(question.getAnswers()[1].getId().toString()))))
                .andExpect(jsonPath("$.answers[1].correct", is(question.getAnswers()[1].isCorrect())))
                .andExpect(jsonPath("$.answers[1].wording", is(question.getAnswers()[1].getWording())));
    }

    @Test
    public void shouldGetOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressIdEmpty() throws Exception {
        Long progressId = 1L;
        QuestionInQuizDTO question = new QuestionInQuizDTO();

        given(questionService.getOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId(any())).willReturn(question);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/notInProgress/{id}", progressId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldNotGetOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId404() throws Exception {
        Long id = 1L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/notInProgress/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldNotGetOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId500() throws Exception {
        Long id = 1L;
        given(questionService.getOneQuestionInQuizAndAllItsAnswersWithIdNotInProgressRecordsByProgressId(id)).willThrow(new Exception());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/questions/notInProgress/{id}", id))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }


}
