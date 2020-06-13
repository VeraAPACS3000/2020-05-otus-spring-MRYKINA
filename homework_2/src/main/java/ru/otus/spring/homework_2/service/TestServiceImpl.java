package ru.otus.spring.homework_2.service;

import ru.otus.spring.homework_2.Utils.TestScanner;
import ru.otus.spring.homework_2.dao.TestDao;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.List;

public class TestServiceImpl implements TestService {
    TestDao testDao;

    public TestServiceImpl(TestDao inTestDaoBuildContainerTest) {
        this.testDao = inTestDaoBuildContainerTest;
    }

    public String printQuestionWithResult() throws IOException {
        List<Test> listObTest = this.testDao.getContainerWithTests();
        Test test = null;
        for (int i = 0; i < listObTest.size(); i++) {
            test = listObTest.get(i);
            int numberAnswerPerson =
                    TestScanner.runScannerAnswerPerson(test.getTextQuestion(), test.getListVariantsAnswer());
            test.setNumberAnswerPerson(numberAnswerPerson);
        }
        String result = testDao.resultAnswerPerson(listObTest);
        return result;
    }
}
