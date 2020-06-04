package ru.otus.spring.homework_1.dao;

import ru.otus.spring.homework_1.domain.TestQuestionAnswer;

import java.util.*;

//import ru.otus.spring.homework_1.domain.

public class TestDaoImpl implements TestDao {

    public TestQuestionAnswer getTestWithQuestionAndAnswer(Map<Integer, String> mapQuestion, Map<String, Integer> mapAnswer, int numberQuestion) {

        TestQuestionAnswer testQuestionAnswer = new TestQuestionAnswer();
        int countAnswer = 0;

        try {
            testQuestionAnswer.setTextQuestion(mapQuestion.get(numberQuestion).toString());

            Set<Map.Entry<String, Integer>> setAnswer = mapAnswer.entrySet();

            Map<Integer, String> mapOutputAnswer = new HashMap<Integer, String>();

            for (Map.Entry<String, Integer> mapAnswerFromSet : setAnswer) {
                if (mapAnswerFromSet.getValue() == numberQuestion) {
                    mapOutputAnswer.put(countAnswer, mapAnswerFromSet.getKey());
                    countAnswer++;
                }
            }
            testQuestionAnswer.setMapAnswers(mapOutputAnswer);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return testQuestionAnswer;
    }

}
