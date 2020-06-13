package ru.otus.spring.homework_2.service.Fakes;

import ru.otus.spring.homework_2.dao.TestDao;
import ru.otus.spring.homework_2.domain.Test;
import ru.otus.spring.homework_2.service.TestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestServiceImplFake implements TestService {
    TestDao testDao;

    public TestServiceImplFake(TestDao inTestDao) {
        this.testDao = inTestDao;
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
