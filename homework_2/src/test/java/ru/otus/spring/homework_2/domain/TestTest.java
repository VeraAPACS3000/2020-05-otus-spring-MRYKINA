package ru.otus.spring.homework_2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Класс TestTest")
class TestTest {

    @DisplayName("Корректно сеттится поле NumberAnswerPerson")
    @Test
    void tetsSetNumberAnswerPerson() {
        ru.otus.spring.homework_2.domain.Test test = new ru.otus.spring.homework_2.domain.Test();
        test.setNumberQuestion(3);
        assertEquals(3, test.getNumberQuestion());
    }

    @DisplayName("Корректно сеттится поле ListVariantsAnswer - not null")
    @Test
    void testSetListVariantsAnswerIsNotNull() {
        ru.otus.spring.homework_2.domain.Test test = new ru.otus.spring.homework_2.domain.Test();
        ArrayList<String> stringListTestSetVariantAnswer = new ArrayList<>();
        stringListTestSetVariantAnswer.add(0, "variant1");

        for (int i = 0; i < stringListTestSetVariantAnswer.size(); i++) {
            test.setListVariantsAnswer(stringListTestSetVariantAnswer.get(i));
        }
        assertThat(test.getListVariantsAnswer()).isNotNull();
    }

    @DisplayName("Корректно сеттится поле NumberQuestion")
    @Test
    void testSetNumberQuestion() {
        ru.otus.spring.homework_2.domain.Test test = new ru.otus.spring.homework_2.domain.Test();
        test.setNumberQuestion(0);
        assertEquals(0, test.getNumberQuestion());
    }

    @DisplayName("Корректно сеттится поле NumberRightAnswer")
    @Test
    void testSetNumberRightAnswer() {
        ru.otus.spring.homework_2.domain.Test test = new ru.otus.spring.homework_2.domain.Test();
        test.setNumberRightAnswer(10);
        assertEquals(10, test.getNumberRightAnswer());
    }

    @DisplayName("Корректно сеттится поле TextQuestion")
    @Test
    void testSetTextQuestion() {
        ru.otus.spring.homework_2.domain.Test test = new ru.otus.spring.homework_2.domain.Test();
        test.setTextQuestion("Text Question");
        assertEquals("Text Question", test.getTextQuestion());
    }
}