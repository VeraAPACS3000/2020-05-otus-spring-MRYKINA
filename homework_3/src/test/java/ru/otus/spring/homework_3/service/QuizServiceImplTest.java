package ru.otus.spring.homework_3.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework_3.dao.IOFile;
import ru.otus.spring.homework_3.dao.QuizDao;
import ru.otus.spring.homework_3.domain.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@DisplayName("Класс QuizDaoImplTest задаёт вопросы, по ответам даёт результат о тестировании пользователя")
@SpringBootTest
class QuizServiceImplTest {

    @MockBean
    private QuizDao quizDao;

    @MockBean
    private QuizScanner quizScanner;

    @Autowired
    private LocalizationService localizationService;

    @MockBean
    private IOFile ioFileMock;

    @Autowired
    private QuizServiceImpl quizServiceImpl;

    /*
        Одни заглушки
     */
    @Test
    @DisplayName("Должен возвращать строку что успешно протестирован")
    void testRunTestingPerson() throws IOException {
        List<String> quizListWithAnswer1 = new ArrayList<>();
        quizListWithAnswer1.addAll(Arrays.asList("4", "2", "22"));

        Quiz quiz1 = new Quiz();
        quiz1.setNumberRightAnswer(1);
        quiz1.setTextQuestion("How much will it be 2 plus 2?");
        for (int i = 0; i < quizListWithAnswer1.size(); i++) {
            quiz1.setListVariantsAnswer(quizListWithAnswer1.get(i));
        }

        List<Quiz> listQuiz = new ArrayList<>();
        listQuiz.addAll(List.of(quiz1));

        given(quizDao.getContainerWithQuiz()).willReturn(List.of(quiz1));
        given(quizDao.resultAnswerPerson(listQuiz)).willReturn("Test done successfully!");
        given(quizScanner.runScannerAnswerPerson("How much will it be 2 plus 2?", quizListWithAnswer1)).willReturn(1);

        //because runScannerFioPerson - void. use doNothing
        doNothing().when(quizScanner).runScannerFioPerson(any());

        assertEquals("Test done successfully!", quizServiceImpl.runQuizPerson());
    }
}