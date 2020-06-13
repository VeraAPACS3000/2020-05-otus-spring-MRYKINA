package ru.otus.spring.homework_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.homework_2.dao.TestDao;
import ru.otus.spring.homework_2.service.Fakes.TestServiceImplFake;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс TestDaoImplTest")
class TestServiceImplTest {

    @Mock
    private TestDao testDao;

    private TestServiceImplFake testServiceImplFake;

    @BeforeEach
    void prepareDataTest(){
        testServiceImplFake = new TestServiceImplFake(testDao);
    }

    @DisplayName("Тест printQuestionWithResult. Пользователь поотвечал и получил сообщение ок/не ок")
    @Test
    /* самый бесполезный тест
    * printQuestionWithResult() - внутри фейк на фейке
    * более полезнее тест на testDao.resultAnswerPerson, класс TestDaoImpl
    * */
    void testPrintQuestionWithResult() throws IOException {
        assertEquals("успешно пройден", testServiceImplFake.printQuestionWithResult());
    }
}