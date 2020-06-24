package ru.otus.spring.homework_3.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.homework_3.config.YamlPropsSettings;
import ru.otus.spring.homework_3.domain.Quiz;
import ru.otus.spring.homework_3.service.LocalizationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


@DisplayName("Класс QuizDaoImplTest")
@SpringBootTest
public class QuizDaoImplTest {

    @MockBean
    YamlPropsSettings yamlPropsSettings;

    @MockBean
    LocalizationService localizationService;

    @MockBean
    IOFile ioFileMock;

    @Autowired
    private QuizDaoImpl testDaoImpl;

    @BeforeEach
    void setUp() {
        given(ioFileMock.getListQuestionTesting()).willReturn(List.of("Question", "How much will it be 2 plus 2?",
                "Answer", "4", "Answer", "2", "Answer", "22", "TrueAnswer", "1", "END"));

        given(yamlPropsSettings.getCountMustRightAnswer()).willReturn(3);
        given(localizationService.getLocaleResultSuccess()).willReturn("Test done successfully!");
        given(localizationService.getLocaleResultFailed()).willReturn("Test failed!");
    }

    @DisplayName("Должен возвращать ожидаемый вопрос для quiz-теста")
    @Test
    void shouldCorrectQuestion() throws IOException {
        Quiz quiz = new Quiz();
        quiz.setTextQuestion("How much will it be 2 plus 2?");
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(0, quiz);
        assertEquals(testDaoImpl.getContainerWithQuiz().get(0).getTextQuestion(), quizList.get(0).getTextQuestion());
    }

    @DisplayName("Должен возвращать ожидаемый правильный вариант ответа для quiz-теста")
    @Test
    void shouldCorrectTrueAnswer() throws IOException {
        Quiz quiz = new Quiz();
        quiz.setNumberRightAnswer(1);
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(0, quiz);
        assertEquals(testDaoImpl.getContainerWithQuiz().get(0).getNumberRightAnswer(), quizList.get(0).getNumberRightAnswer());
    }

    @DisplayName("Должен возвращать список не пустых Куиз-Тестов")
    @Test
    void shouldHaveNotNullContainerWithTests() throws IOException {
        List<String> listString = new ArrayList<>();
        listString.add(0, "How much will it be 2 plus 2?");
        listString.add(0, "4");
        listString.add(0, "2");
        listString.add(0, "22");
        listString.add(0, "1");

        List<String> testListWithAnswer = new ArrayList<>();
        testListWithAnswer.add("4");
        testListWithAnswer.add("2");
        testListWithAnswer.add("22");

        Quiz testFortQuizHaveNotNull = new Quiz();
        testFortQuizHaveNotNull.setTextQuestion("How much will it be 2 plus 2?");
        for (int i = 0; i < testListWithAnswer.size(); i++) {
            testFortQuizHaveNotNull.setListVariantsAnswer(testListWithAnswer.get(i));
        }
        testFortQuizHaveNotNull.setNumberRightAnswer(1);
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(0, testFortQuizHaveNotNull);

        assertThat(testDaoImpl.getContainerWithQuiz()).isNotNull();
    }

    @DisplayName("Значения в списке вариантов ответов ожидаемы")
    @Test
    void shouldHaveEqualsValuesListAnswer() throws IOException {
        List<Quiz> listQuiz = testDaoImpl.getContainerWithQuiz();
        Quiz quizFromList = listQuiz.get(0);
        List<String> listAnswer = quizFromList.getListVariantsAnswer();

        assertAll("Answer",
                () -> assertEquals("4", listAnswer.get(0)),
                () -> assertEquals("2", listAnswer.get(1)),
                () -> assertEquals("22", listAnswer.get(2)));
    }

    @DisplayName("Должен верно подсчитывать кол-во ответов при успешной здачи quiz-теста")
    @Test
    void testResultRightAnswerPerson() {
        Quiz quiz1 = new Quiz();
        quiz1.setNumberAnswerPerson(1);
        quiz1.setNumberRightAnswer(1);

        Quiz quiz2 = new Quiz();
        quiz2.setNumberAnswerPerson(1);
        quiz2.setNumberRightAnswer(1);

        Quiz quiz3 = new Quiz();
        quiz3.setNumberAnswerPerson(1);
        quiz3.setNumberRightAnswer(1);

        List<Quiz> listStringRightAnswer = new ArrayList<Quiz>();
        listStringRightAnswer.add(0, quiz1);
        listStringRightAnswer.add(1, quiz2);
        listStringRightAnswer.add(2, quiz3);

        assertEquals("Test done successfully!", testDaoImpl.resultAnswerPerson(listStringRightAnswer));
    }

    @DisplayName("Должен верно подсчитывать кол-во ответов при  НЕ успешной здачи quiz-теста")
    @Test
    void testResultNotRightAnswerPerson() {
        Quiz quiz1 = new Quiz();
        quiz1.setNumberAnswerPerson(3);
        quiz1.setNumberRightAnswer(1);

        Quiz quiz2 = new Quiz();
        quiz2.setNumberAnswerPerson(3);
        quiz2.setNumberRightAnswer(1);

        Quiz quiz3 = new Quiz();
        quiz3.setNumberAnswerPerson(3);
        quiz3.setNumberRightAnswer(1);

        List<Quiz> listStringNotRightAnswer = new ArrayList<Quiz>();
        listStringNotRightAnswer.add(0, quiz1);
        listStringNotRightAnswer.add(1, quiz2);
        listStringNotRightAnswer.add(2, quiz3);

        assertEquals("Test failed!", testDaoImpl.resultAnswerPerson(listStringNotRightAnswer));
    }
}