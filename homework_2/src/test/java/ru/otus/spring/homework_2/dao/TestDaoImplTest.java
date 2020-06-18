package ru.otus.spring.homework_2.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс TestDaoImplTest")
public class TestDaoImplTest {

    private static TestDaoImpl testDaoImpl;

    @BeforeAll
    static void setUp() {
        IOFile IOFileStub = new IOFileImplGetDataStub("stubNameFile");//stub instedof mock
        testDaoImpl = new TestDaoImpl(IOFileStub, 3);
    }

    @DisplayName("Тест getContainerWithTests. Возвращает список наших Тестов, не null")
    @org.junit.jupiter.api.Test
    void shouldHaveNotNullContainerWithTests() throws IOException {
        List<String> listString = new ArrayList<String>();
        listString.add(0, "How much will it be 2 plus 2?");
        listString.add(0, "4");
        listString.add(0, "2");
        listString.add(0, "22");
        listString.add(0, "1");

        List<String> testListWithAnswer = new ArrayList<String>();
        testListWithAnswer.add("4");
        testListWithAnswer.add("2");
        testListWithAnswer.add("22");

        Test testFortTestHaveNotNull = new Test();
        testFortTestHaveNotNull.setTextQuestion("How much will it be 2 plus 2?");
        for (int i = 0; i < testListWithAnswer.size(); i++) {
            testFortTestHaveNotNull.setListVariantsAnswer(testListWithAnswer.get(i));
        }
        testFortTestHaveNotNull.setNumberRightAnswer(1);
        List<Test> testList = new ArrayList<Test>();
        testList.add(0, testFortTestHaveNotNull);

        assertThat(testDaoImpl.getContainerWithTests())
                .isNotNull();
    }

    @DisplayName("Тест getContainerWithTests. Значения в списке вариантов ответов Test ожидаемы")
    @org.junit.jupiter.api.Test
    void shouldHaveEqualsValuesListAnswer() throws IOException {
        List<Test> listTest = null;
        listTest = testDaoImpl.getContainerWithTests();
        Test testFromList= listTest.get(0);
        List<String> listAnswer = testFromList.getListVariantsAnswer();

        assertAll("Answer",
                () -> assertEquals("4", listAnswer.get(0)),
                () -> assertEquals("2", listAnswer.get(1)),
                () -> assertEquals("22", listAnswer.get(2)));
    }

    @DisplayName("Тест resultAnswerPerson. Подсчёт ответов при успешной здачи теста")
    @org.junit.jupiter.api.Test
    void testResultRightAnswerPerson() {
        Test test1 = new Test();
        test1.setNumberAnswerPerson(1);
        test1.setNumberRightAnswer(1);

        Test test2 = new Test();
        test2.setNumberAnswerPerson(1);
        test2.setNumberRightAnswer(1);

        Test test3 = new Test();
        test3.setNumberAnswerPerson(1);
        test3.setNumberRightAnswer(1);

        List<Test> listStringRightAnswer = new ArrayList<Test>();
        listStringRightAnswer.add(0, test1);
        listStringRightAnswer.add(1, test2);
        listStringRightAnswer.add(2, test3);

        assertEquals("Тест пройден успешно", testDaoImpl.resultAnswerPerson(listStringRightAnswer));
    }

    @DisplayName("Тест resultAnswerPerson. Подсчёт ответов при не успешной здачи теста")
    @org.junit.jupiter.api.Test
    void testResultNotRightAnswerPerson() {
        Test test1 = new Test();
        test1.setNumberAnswerPerson(3);
        test1.setNumberRightAnswer(1);

        Test test2 = new Test();
        test2.setNumberAnswerPerson(3);
        test2.setNumberRightAnswer(1);

        Test test3 = new Test();
        test3.setNumberAnswerPerson(3);
        test3.setNumberRightAnswer(1);

        List<Test> listStringNotRightAnswer = new ArrayList<Test>();
        listStringNotRightAnswer.add(0, test1);
        listStringNotRightAnswer.add(1, test2);
        listStringNotRightAnswer.add(2, test3);

        assertEquals("Тест пройден не успешно", testDaoImpl.resultAnswerPerson(listStringNotRightAnswer));
    }
}