package ru.otus.spring.homework_1.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Test")
public class TestQuestionAnswerTest {
    @DisplayName("Корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Map<Integer, String> hashMapAnswerExcept = new HashMap<Integer, String>();
        hashMapAnswerExcept.put(1, "Moscow");

        Map<Integer, String> hashMapAnswer = new HashMap<Integer, String>();
        hashMapAnswer.put(1, "Moscow");
        String inTextQuestion = "Moscow";

        TestQuestionAnswer test =
                new TestQuestionAnswer(inTextQuestion, hashMapAnswer);

        assertEquals(inTextQuestion, test.getTextQuestion());
        assertEquals(hashMapAnswerExcept, test.getMapAnswers());
    }
}

