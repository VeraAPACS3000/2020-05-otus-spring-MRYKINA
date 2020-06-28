package ru.otus.spring.homework_4.dao;

import ru.otus.spring.homework_4.exception.QuizException;

public class QuizFileNotFoundException extends QuizException {
    public QuizFileNotFoundException(Throwable cause) {
        super(cause);
    }

    public QuizFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
