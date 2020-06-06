package ru.otus.spring.homework_1.dao;

import ru.otus.spring.homework_1.domain.TestQuestionAnswer;

import java.util.Map;

public interface TestDao {
    TestQuestionAnswer getTestWithQuestionAndAnswer(Map<Integer, String> mapQuestion, Map<String, Integer> mapAnswer, int numberQuestion);
}
