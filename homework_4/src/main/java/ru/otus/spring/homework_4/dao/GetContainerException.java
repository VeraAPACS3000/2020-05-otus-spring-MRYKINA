package ru.otus.spring.homework_4.dao;

import ru.otus.spring.homework_4.exception.QuizException;

public class GetContainerException extends QuizException {
    public GetContainerException(Throwable cause) {
        super(cause);
    }
}
