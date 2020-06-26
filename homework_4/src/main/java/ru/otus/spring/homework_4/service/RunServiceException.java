package ru.otus.spring.homework_4.service;

import ru.otus.spring.homework_4.exception.QuizException;

public class RunServiceException extends QuizException {
    public RunServiceException(Throwable cause) {
        super(cause);
    }

    public RunServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
