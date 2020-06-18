package ru.otus.spring.homework_2.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework_2.dao.TestDao;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.List;

@Component
public class TestServiceImpl implements TestService {

    private final TestDao testDao;
    private final String questionFirstName;
    private final String questionLastName;
    private final String questionMiddleName;

    public TestServiceImpl(TestDao inTestDao, String inQuestionFirstName, String inQuestionLastName, String inQuestionMiddleName) {
        this.testDao = inTestDao;
        this.questionFirstName = inQuestionFirstName;
        this.questionLastName = inQuestionLastName;
        this.questionMiddleName = inQuestionMiddleName;
    }

    public String printQuestionWithResult() throws IOException {
        //понимаю, что один метод - одно действие, но здесь походу архитектуру всего приложения необходимо менять
        //чтобы убрать отсюда этот вызов метода
        printsQuestionPersonalData();

        List<Test> listObTest = this.testDao.getContainerWithTests();
        Test test = null;
        for (int i = 0; i < listObTest.size(); i++) {
            test = listObTest.get(i);
            int numberAnswerPerson =
                    TestScanner.runScannerAnswerPerson(test.getTextQuestion(), test.getListVariantsAnswer());
            test.setNumberAnswerPerson(numberAnswerPerson);
        }
        String result = testDao.resultAnswerPerson(listObTest);

        System.out.println(result);

        return result;
    }

    private void printsQuestionPersonalData() {
        TestScanner.runScannerFioPerson(this.questionFirstName);
        TestScanner.runScannerFioPerson(this.questionLastName);
        TestScanner.runScannerFioPerson(this.questionMiddleName);
    }
}
