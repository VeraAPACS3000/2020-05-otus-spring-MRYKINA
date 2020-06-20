package ru.otus.spring.homework_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.homework_2.dao.ITestDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс TestDaoImplTest")
class TestServiceImplITest {

    @Mock
    private ITestDao testDao;

    private TestScanner testScanner = mock(TestScanner.class);

    private TestServiceImpl testServiceImpl;

    @BeforeEach
    void prepareDataTest() throws IOException {

        List<String> testListWithAnswer1 = new ArrayList<String>();
        testListWithAnswer1.add("4");
        testListWithAnswer1.add("2");
        testListWithAnswer1.add("22");

        List<String> testListWithAnswer2 = new ArrayList<String>();
        testListWithAnswer2.add("Berlin");
        testListWithAnswer2.add("Paris");
        testListWithAnswer2.add("Kiev");

        List<String> testListWithAnswer3 = new ArrayList<String>();
        testListWithAnswer3.add("Interface");
        testListWithAnswer3.add("Class");
        testListWithAnswer3.add("Abstract cLass");

        ru.otus.spring.homework_2.domain.Test test1 = new ru.otus.spring.homework_2.domain.Test();
        test1.setNumberRightAnswer(1);
        test1.setTextQuestion("How much will it be 2 plus 2?");
        for (int i = 0; i < testListWithAnswer1.size(); i++) {
            test1.setListVariantsAnswer(testListWithAnswer1.get(i));
        }

        ru.otus.spring.homework_2.domain.Test test2 = new ru.otus.spring.homework_2.domain.Test();
        test2.setNumberRightAnswer(1);
        test2.setTextQuestion("what is the capital of Germany?");
        for (int i = 0; i < testListWithAnswer2.size(); i++) {
            test2.setListVariantsAnswer(testListWithAnswer2.get(i));
        }

        ru.otus.spring.homework_2.domain.Test test3 = new ru.otus.spring.homework_2.domain.Test();
        test3.setNumberRightAnswer(1);
        test3.setTextQuestion("Collection is?");
        for (int i = 0; i < testListWithAnswer3.size(); i++) {
            test3.setListVariantsAnswer(testListWithAnswer3.get(i));
        }

        List<ru.otus.spring.homework_2.domain.Test> listTest = new ArrayList<>();
        listTest.addAll(List.of(test1, test2, test3));

        given(testDao.getContainerWithTests()).willReturn(List.of(test1, test2, test3));
        given(testDao.resultAnswerPerson(listTest)).willReturn("Success completed");
        given(testScanner.runScannerAnswerPerson("How much will it be 2 plus 2?", testListWithAnswer1)).willReturn(1);
        given(testScanner.runScannerAnswerPerson("what is the capital of Germany?", testListWithAnswer2)).willReturn(1);
        given(testScanner.runScannerAnswerPerson("Collection is?", testListWithAnswer3)).willReturn(1);

        //because runScannerFioPerson - void. use doNothing
        doNothing().when(testScanner).runScannerFioPerson(any());

        testServiceImpl = new TestServiceImpl(testDao, testScanner, "Vova", "Pupkin", "Petrovich");
    }

    /*
        Бесполезный тест
    */
    @DisplayName("Тест runTestingPerson")
    @Test
    void testRunTestingPerson() throws IOException {
        assertEquals("Success completed", testServiceImpl.runTestingPerson());
    }
}