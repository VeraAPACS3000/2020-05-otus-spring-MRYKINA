package ru.otus.spring.homework_4.exception;

public class QuizException extends RuntimeException {
    public QuizException(Throwable cause) {
        super(cause);
    }

    public QuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
