package ru.otus.spring.homework12.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String getNotFoundPage(Model model) {
        model.addAttribute("message", HttpStatus.NOT_FOUND);
        return "globalError";
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String getBadRequest(Model model) {
        model.addAttribute("message", HttpStatus.BAD_REQUEST);
        return "globalError";
    }

    @ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
    public String getGateWayTimeOut(Model model) {
        model.addAttribute("message", HttpStatus.GATEWAY_TIMEOUT);
        return "globalError";
    }
}
