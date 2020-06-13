package ru.otus.spring.homework_2.dao;

import ru.otus.spring.homework_2.Utils.Utils;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.otus.spring.homework_2.Utils.Constants.*;

public class TestDaoImpl implements TestDao {
    private final Utils utilIOFile;
    private final int countMustRightAnswer;

    public TestDaoImpl(Utils utilIOFile, int inCountMustRightAnswer) {
        this.utilIOFile = utilIOFile;
        this.countMustRightAnswer = inCountMustRightAnswer;
    }

    @Override
    public List<Test> getContainerWithTests() throws IOException {
        List<String> list = this.utilIOFile.getListStringsFromFile();
        List<Test> listObTest = new ArrayList<Test>();
        Test test = new Test();
        int nextString = 1;
        int numberQuestion = 0;
        for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            if (string.equals(HEAD_QUESTION_FROM_FILE)) {
                test.setTextQuestion(list.get(i + nextString));
            } else if (string.equals(HEAD_ANSWER_FROM_FILE)) {
                test.setListVariantsAnswer(list.get(i + nextString));
            } else if (string.equals(HEAD_TRUEANSWER_FROM_FILE)) {
                test.setNumberRightAnswer(Integer.parseInt(list.get(i + nextString)));
            } else if (string.equals(HEAD_END_BLOCK_QUESTION_FROM_FILE)) {
                ++numberQuestion;
                test.setNumberQuestion(numberQuestion);
                listObTest.add(test);
                test = new Test();
            }
        }
        return listObTest;
    }



    public String resultAnswerPerson(List<Test> listObTest) {
        int countRightAnswers = calculateCountRightAnswers(listObTest);
        String resultMessage = checkCountRightAnswers(countRightAnswers, countMustRightAnswer);
        return resultMessage;
    }

    private String checkCountRightAnswers(int countRightAnswers, int countMustRightAnswer) {
        String resultMessage = "Тест пройден не успешно";
        if (countRightAnswers >= this.countMustRightAnswer) {
            resultMessage = "Тест пройден успешно";
        }
        return resultMessage;
    }

    private int calculateCountRightAnswers(List<Test> listObTest) {
        Test test = null;
        int countRightAnswers = 0;
        for (int i = 0; i < listObTest.size(); i++) {
            test = listObTest.get(i);
            int numberRight = test.getNumberRightAnswer();
            int numberAnswerPerson = test.getNumberAnswerPerson();
            if (numberRight == numberAnswerPerson) {
                countRightAnswers++;
            }
        }

        return countRightAnswers;
    }

}
