package ru.otus.spring.homework_2.service;

import ru.otus.spring.homework_2.dao.TestDao;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestServiceImplStub implements TestService {
    private TestDao testDao;
    private String questionFirstName;
    private String questionLastName;
    private String questionMiddleName;

    public TestServiceImplStub(TestDao inTestDao, String questionFirstName, String questionLastName, String questionMiddleName) {
        this.testDao = inTestDao;
        this.questionFirstName = questionFirstName;
        this.questionLastName = questionLastName;
        this.questionMiddleName = questionMiddleName;
    }

    @Override
    public String printQuestionWithResult() throws IOException {
        List<Test> listObTest = getFakeContainerWithTests();/*Здесь была оболочка his.testDao.getContainerWithTests();*/
        Test test = null;
        for (int i = 0; i < listObTest.size(); i++) {
            test = listObTest.get(i);
            int numberAnswerPerson = 5;//fake
            /*Здесь оболочка пользовательский ввод TestScanner.runScannerAnswerPerson(test.getTextQuestion(), test.getListVariantsAnswer());*/
            test.setNumberAnswerPerson(numberAnswerPerson);
        }
        String result = "Success completed"; /*Было testDao.resultAnswerPerson(listObTest);*/
        return result;
    }

    private List<Test> getFakeContainerWithTests() {
        Test test = new Test();
        List<Test> listTest = new ArrayList<Test>();
        listTest.add(0, test);
        return listTest;
    }
}
