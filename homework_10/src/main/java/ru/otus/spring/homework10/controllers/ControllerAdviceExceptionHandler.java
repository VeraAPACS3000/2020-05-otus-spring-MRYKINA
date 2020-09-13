package ru.otus.spring.homework10.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(NotFoundBookException.class)
    public ModelAndView handleNotFoundBookException(NotFoundBookException ex) {
        ModelAndView modelAndView = new ModelAndView("errNotFoundBook");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
}
