package ru.otus.spring.homework_1.service;

import ru.otus.spring.homework_1.domain.TestQuestionAnswer;

import java.util.Map;

public interface TestService {

    TestQuestionAnswer getTestWithQuestionANdAnswer(Map<Integer, String> mapQuestion, Map<String, Integer> mapAnswer, int numberQuestion);
}
