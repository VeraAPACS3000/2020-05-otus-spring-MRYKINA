package ru.otus.spring.homework_2.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static ru.otus.spring.homework_2.Utils.Constants.*;
import static ru.otus.spring.homework_2.Utils.Constants.HEAD_END_BLOCK_QUESTION_FROM_FILE;

@DisplayName("Класс TestDaoImplTest")
public class TestDaoImplTest {

    @Mock
    private TestDaoImpl testDaoImpl;

    @Mock
    private Test test;

    private List<String> listString = null;

    @BeforeEach
    void setUp() {
        listString = new ArrayList<String>();
        listString.add(0, "Question");
        listString.add(0, "How much will it be 2 plus 2?");
        listString.add(0, "Answer");
        listString.add(0, "4");
        listString.add(0, "Answer");
        listString.add(0, "2");
        listString.add(0, "Answer");
        listString.add(0, "22");
        listString.add(0, "TrueAnswer");
        listString.add(0, "1");
        listString.add(0, "END");
    }

    //старая попытка
    @org.junit.jupiter.api.Test
    void printQuestionWithResult() throws IOException {
        when(testDaoImpl.parseListTest()).thenReturn(test);
    }
}

class testParseListTest{
    /*
          Здесь весь код PRIVATE метода parseListTest из класса TestDaoImpl
   */

    public List<Test> parseListTest(List<String> list) {
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
}
