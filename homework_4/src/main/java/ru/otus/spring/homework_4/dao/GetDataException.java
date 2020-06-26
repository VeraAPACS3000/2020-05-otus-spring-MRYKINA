package ru.otus.spring.homework_4.dao;

import ru.otus.spring.homework_4.exception.QuizException;

public class GetDataException extends QuizException {
    public GetDataException(Throwable cause) {
        super(cause);
    }

    public GetDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
