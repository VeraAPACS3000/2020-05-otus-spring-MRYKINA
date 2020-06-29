package ru.otus.spring.homework_4.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс локализация файлов с вопросами")
@SpringBootTest
public class LocalizationServiceTest {

    private LocalizationService localizationService;

    @Autowired
    private MessageSource messageSource;

    @ParameterizedTest
    @MethodSource("generateDataNameFileQuestionQuiz")
    @DisplayName("Должен возвращать корректное имя файла с вопросами для quiz. Все языки")
    public void shouldNameFileQuestionQuizIsCorrect(String language, String country, String resultNameFile) {
        Locale currentLocale = new Locale(language, country);
        Locale.setDefault(currentLocale);
        System.out.println(Locale.getDefault());
        LocalizationService localizationService = new LocalizationService(messageSource);
        assertEquals(resultNameFile, localizationService.getLocaleFileQuestionForQuiz());
    }

    private static Stream<Arguments> generateDataNameFileQuestionQuiz() {
        return Stream.of(
                Arguments.of("ru", "RU", "questions_ru_RU.csv"),
                Arguments.of("en", "", "questions.csv")
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataNameFileQuestionPerson")
    @DisplayName("Должен возвращать корректное имя файла с вопросами по пользователю ФИО. Все языки")
    public void shouldNameFileQuestionPersonIsCorrect(String language, String country, String resultNameFile) {
        Locale currentLocale = new Locale(language, country);
        Locale.setDefault(currentLocale);
        System.out.println(Locale.getDefault());
        LocalizationService localizationService = new LocalizationService(messageSource);
        assertEquals(resultNameFile, localizationService.getLocaleFileQuestionPerson());
    }

    private static Stream<Arguments> generateDataNameFileQuestionPerson() {
        return Stream.of(
                Arguments.of("ru", "RU", "questionsPersonData_ru_RU.csv"),
                Arguments.of("en", "", "questionsPersonData.csv")
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataCorrectStringResultQuizSuccess")
    @DisplayName("Должен возвращать корректную успешную строку-результат после quiz-теста Успех. Все языки")
    public void shouldCorrectStringResultQuizSuccess(String language, String country, String resultNameFile) {
        Locale currentLocale = new Locale(language, country);
        Locale.setDefault(currentLocale);
        System.out.println(Locale.getDefault());
        LocalizationService localizationService = new LocalizationService(messageSource);
        assertEquals(resultNameFile, localizationService.getLocaleResultSuccess());
    }

    private static Stream<Arguments> generateDataCorrectStringResultQuizSuccess() {
        return Stream.of(
                Arguments.of("ru", "RU", "Тест сдан успешно!"),
                Arguments.of("en", "", "Test done successfully!")
        );
    }

    @ParameterizedTest
    @MethodSource("generateDataCorrectStringResultQuizNotSuccess")
    @DisplayName("Должен возвращать корректную успешную строку-результат после quiz-теста Не успех. Все языки")
    public void shouldCorrectStringResultQuizNotSuccess(String language, String country, String resultNameFile) {
        Locale currentLocale = new Locale(language, country);
        Locale.setDefault(currentLocale);
        System.out.println(Locale.getDefault());
        LocalizationService localizationService = new LocalizationService(messageSource);
        assertEquals(resultNameFile, localizationService.getLocaleResultFailed());
    }

    private static Stream<Arguments> generateDataCorrectStringResultQuizNotSuccess() {
        return Stream.of(
                Arguments.of("ru", "RU", "Тест пройден не успешно!"),
                Arguments.of("en", "", "Test failed!")
        );
    }

}

