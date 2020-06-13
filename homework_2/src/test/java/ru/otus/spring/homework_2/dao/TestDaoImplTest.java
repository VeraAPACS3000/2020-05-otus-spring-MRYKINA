package ru.otus.spring.homework_2.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import ru.otus.spring.homework_2.Utils.FakeUtilIOFile;
import ru.otus.spring.homework_2.Utils.Utils;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс TestDaoImplTest")
public class TestDaoImplTest {
    private List<String> listString = null;
    private TestDaoImpl testDaoImpl = null;
    private List<Test> testList = null;

    @BeforeEach
    void prepareResultTest() {
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

    @BeforeEach
    void setUp() {
        Utils utilsFake = new FakeUtilIOFile("fakeNameFile");
        testDaoImpl = new TestDaoImpl(utilsFake, 3);
    }

    @DisplayName("Возвращает список наших Тестов, не null")
    @org.junit.jupiter.api.Test
    void shouldHaveNotNullContainerWithTests() throws IOException {
        assertThat(testDaoImpl.getContainerWithTests())
                .isNotNull();
    }

    @DisplayName("Значения в обьекте Test ожидаемы")
    @org.junit.jupiter.api.Test
    void shouldHaveEqualsContainerWithTests() throws IOException {
        //TODO: придумать
        //assertEquals("", testDaoImpl.getContainerWithTests());
    }
}

