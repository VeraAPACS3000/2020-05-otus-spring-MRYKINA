package ru.otus.spring.homework_4.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Класс QuizTest")
@SpringBootTest
class QuizTest {

    @DisplayName("Корректно сеттится поле NumberAnswerPerson")
    @Test
    void testSetNumberAnswerPerson() {
        Quiz quiz = new Quiz();
        quiz.setNumberQuestion(3);
        assertEquals(3, quiz.getNumberQuestion());
    }

    @DisplayName("Корректно сеттится поле ListVariantsAnswer - not null")
    @Test
    void testSetListVariantsAnswerIsNotNull() {
        Quiz quiz = new Quiz();
        ArrayList<String> stringListTestSetVariantAnswer = new ArrayList<>();
        stringListTestSetVariantAnswer.add(0, "variant1");

        for (int i = 0; i < stringListTestSetVariantAnswer.size(); i++) {
            quiz.setListVariantsAnswer(stringListTestSetVariantAnswer.get(i));
        }
        assertThat(quiz.getListVariantsAnswer()).isNotNull();
    }

    @DisplayName("Корректно сеттится поле NumberQuestion")
    @Test
    void testSetNumberQuestion() {
        Quiz quiz = new Quiz();
        quiz.setNumberQuestion(0);
        assertEquals(0, quiz.getNumberQuestion());
    }

    @DisplayName("Корректно сеттится поле NumberRightAnswer")
    @Test
    void testSetNumberRightAnswer() {
        Quiz quiz = new Quiz();
        quiz.setNumberRightAnswer(10);
        assertEquals(10, quiz.getNumberRightAnswer());
    }

    @DisplayName("Корректно сеттится поле TextQuestion")
    @Test
    void testSetTextQuestion() {
        Quiz quiz = new Quiz();
        quiz.setTextQuestion("Text Quiz");
        assertEquals("Text Quiz", quiz.getTextQuestion());
    }
}