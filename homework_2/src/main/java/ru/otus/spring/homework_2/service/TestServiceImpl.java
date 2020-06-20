package ru.otus.spring.homework_2.service;

import ru.otus.spring.homework_2.dao.ITestDao;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.List;

public class TestServiceImpl implements ITestService {

    private final ITestDao testDao;
    private final TestScanner testScanner;
    private final String questionFirstName;
    private final String questionLastName;
    private final String questionMiddleName;

    public TestServiceImpl(ITestDao inTestDao, TestScanner testScanner, String inQuestionFirstName,
                           String inQuestionLastName, String inQuestionMiddleName) {
        this.testDao = inTestDao;
        this.testScanner = testScanner;
        this.questionFirstName = inQuestionFirstName;
        this.questionLastName = inQuestionLastName;
        this.questionMiddleName = inQuestionMiddleName;
    }

    public String runTestingPerson() throws IOException {
        printsQuestionPersonalData();

        List<Test> listObTest = this.testDao.getContainerWithTests();
        Test test = null;
        for (int i = 0; i < listObTest.size(); i++) {
            test = listObTest.get(i);
            int numberAnswerPerson =
                    testScanner.runScannerAnswerPerson(test.getTextQuestion(), test.getListVariantsAnswer());
            test.setNumberAnswerPerson(numberAnswerPerson);
        }
        String result = testDao.resultAnswerPerson(listObTest);

        System.out.println(result);

        return result;
    }

    private void printsQuestionPersonalData() {
        testScanner.runScannerFioPerson(this.questionFirstName);
        testScanner.runScannerFioPerson(this.questionLastName);
        testScanner.runScannerFioPerson(this.questionMiddleName);
    }
}
