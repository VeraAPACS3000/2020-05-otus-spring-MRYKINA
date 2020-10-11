package ru.otus.spring.homework12.controllers;

public class NotFoundBookException extends RuntimeException {
    public NotFoundBookException(String message) {
        super("Not found book in Data Base. " + message);
    }
}
