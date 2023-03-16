package com.azerty.quizgame.model.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordTests {

    @Test
    public void shouldGetAndSetAllAttributesForRecord() {
        Long id = 1L;
        boolean isSuccess = true;
        Question question = new Question();
        Progress progress = new Progress();

        Record record = new Record();
        record.setId(id);
        record.setSuccess(isSuccess);
        record.setQuestion(question);
        record.setProgress(progress);

        Assertions.assertEquals(id, record.getId());
        Assertions.assertEquals(isSuccess, record.isSuccess());
        Assertions.assertEquals(question, record.getQuestion());
        Assertions.assertEquals(progress, record.getProgress());
        Assertions.assertEquals("Record{" +
                "id=" + id +
                ", isSuccess=" + isSuccess +
                ", question=" + question +
                ", progress=" + progress +
                '}', record.toString());
    }

}
