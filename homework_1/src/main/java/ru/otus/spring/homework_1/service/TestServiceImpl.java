package ru.otus.spring.homework_1.service;

import ru.otus.spring.homework_1.dao.TestDao;
import ru.otus.spring.homework_1.domain.TestQuestionAnswer;

import java.util.Map;

public class TestServiceImpl implements TestService {

    private final TestDao testDao;

    public TestServiceImpl(TestDao testDao) {
        this.testDao = testDao;
    }

    public TestQuestionAnswer getTestWithQuestionANdAnswer(Map<Integer, String> mapQuestion, Map<String, Integer> mapAnswer, int numberQuestion/*Map inMapQuestionAndAnswer*/) {
        return testDao.getTestWithQuestionAndAnswer(mapQuestion, mapAnswer, numberQuestion);
    }
}
