package ru.otus.spring.homework_2.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import ru.otus.spring.homework_2.Utils.Fake.UtilIOFileImplFake;
import ru.otus.spring.homework_2.Utils.Utils;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс TestDaoImplTest")
public class TestDaoImplTest {

    private List<String> listString = null;
    private static TestDaoImpl testDaoImpl = null;
    private List<Test> testList = null;
    private List<Test> listStringRightAnswer = null;
    private List<Test> listStringNotRightAnswer = null;
    private Test testFromList = null;
    private List<String> listAnswer = null;

    @BeforeAll
    static void setUp() {
        Utils utilsFake = new UtilIOFileImplFake("fakeNameFile");
        testDaoImpl = new TestDaoImpl(utilsFake, 3);
    }

    @BeforeEach
    void prepareResultHaveNotNullTest() {
        //must result test
        listString = new ArrayList<String>();
        listString.add(0, "How much will it be 2 plus 2?");
        listString.add(0, "4");
        listString.add(0, "2");
        listString.add(0, "22");
        listString.add(0, "1");

        List<String> testListWithAnswer = new ArrayList<String>();
        testListWithAnswer.add("4");
        testListWithAnswer.add("2");
        testListWithAnswer.add("22");

        Test test = new Test();
        test.setTextQuestion("How much will it be 2 plus 2?");
        for (int i = 0; i < testListWithAnswer.size(); i++) {
            test.setListVariantsAnswer(testListWithAnswer.get(i));
        }
        test.setNumberRightAnswer(1);
        testList = new ArrayList<Test>();
        testList.add(0, test);
    }

    @DisplayName("Тест getContainerWithTests. Возвращает список наших Тестов, не null")
    @org.junit.jupiter.api.Test
    void shouldHaveNotNullContainerWithTests() throws IOException {
        assertThat(testDaoImpl.getContainerWithTests())
                .isNotNull();
    }

    @BeforeEach
    void prepareDataTestEqualsValues() throws IOException {
        List<Test> listTest = null;
        listTest = testDaoImpl.getContainerWithTests();
        testFromList = listTest.get(0);
    }

    @DisplayName("Тест getContainerWithTests. Значения в обьекте Test из списка Тестов ожидаемы")
    @org.junit.jupiter.api.Test
    void shouldHaveEqualsValues() {

        assertAll("Test",
                () -> assertEquals("How much will it be 2 plus 2?", testFromList.getTextQuestion()),
                () -> assertEquals(1, testFromList.getNumberRightAnswer()),
                () -> assertEquals(0, testFromList.getNumberAnswerPerson()));
    }

    @BeforeEach
    void prepareListAnswerTestEqualsValues() throws IOException {
        List<Test> listTest = null;
        listTest = testDaoImpl.getContainerWithTests();
        testFromList = listTest.get(0);
        listAnswer = testFromList.getListVariantsAnswer();
    }

    @DisplayName("Тест getContainerWithTests. Значения в списке вариантов ответов Test ожидаемы")
    @org.junit.jupiter.api.Test
    void shouldHaveEqualsValuesListAnswer() {

        assertAll("Answer",
                () -> assertEquals("4", listAnswer.get(0)),
                () -> assertEquals("2", listAnswer.get(1)),
                () -> assertEquals("22", listAnswer.get(2)));
    }

    @BeforeEach
    void prepareDataTestResultAnswerRight() {
        Test test1 = new Test();
        test1.setNumberAnswerPerson(1);
        test1.setNumberRightAnswer(1);

        Test test2 = new Test();
        test2.setNumberAnswerPerson(1);
        test2.setNumberRightAnswer(1);

        Test test3 = new Test();
        test3.setNumberAnswerPerson(1);
        test3.setNumberRightAnswer(1);

        listStringRightAnswer = new ArrayList<Test>();
        listStringRightAnswer.add(0, test1);
        listStringRightAnswer.add(1, test2);
        listStringRightAnswer.add(2, test3);
    }

    @DisplayName("Тест resultAnswerPerson. Подсчёт ответов при успешной здачи теста")
    @org.junit.jupiter.api.Test
    void testResultRightAnswerPerson() {
        assertEquals("Тест пройден успешно", testDaoImpl.resultAnswerPerson(listStringRightAnswer));
    }

    @BeforeEach
    void prepareDataTestResultAnswerNotRight() {
        Test test1 = new Test();
        test1.setNumberAnswerPerson(3);
        test1.setNumberRightAnswer(1);

        Test test2 = new Test();
        test2.setNumberAnswerPerson(3);
        test2.setNumberRightAnswer(1);

        Test test3 = new Test();
        test3.setNumberAnswerPerson(3);
        test3.setNumberRightAnswer(1);

        listStringNotRightAnswer = new ArrayList<Test>();
        listStringNotRightAnswer.add(0, test1);
        listStringNotRightAnswer.add(1, test2);
        listStringNotRightAnswer.add(2, test3);
    }

    @DisplayName("Тест resultAnswerPerson. Подсчёт ответов при не успешной здачи теста")
    @org.junit.jupiter.api.Test
    void testResultNotRightAnswerPerson() {
        assertEquals("Тест пройден не успешно", testDaoImpl.resultAnswerPerson(listStringNotRightAnswer));
    }

}

