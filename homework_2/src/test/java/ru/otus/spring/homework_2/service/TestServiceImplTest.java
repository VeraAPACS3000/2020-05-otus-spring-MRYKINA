package ru.otus.spring.homework_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.homework_2.dao.TestDao;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс TestDaoImplTest")
class TestServiceImplTest {

    @Mock
    private TestDao testDao;

    private String questionFirstName;
    private String questionLastName;
    private String questionMiddleName;

    private TestServiceImplStub testServiceImplStub;

    @BeforeEach
    void prepareDataTest(){
        testServiceImplStub = new TestServiceImplStub(testDao, questionFirstName, questionLastName, questionMiddleName);
    }

    @DisplayName("Тест printQuestionWithResult. Пользователь поотвечал и получил сообщение ок/не ок")
    @Test
    void testPrintQuestionWithResult() throws IOException {
        assertEquals("Success completed", testServiceImplStub.printQuestionWithResult());
    }
}