package ru.otus.spring.homework_2.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @org.junit.jupiter.api.Test
    //тест метода - с четко своей задачей
    void getContainerWithTests() throws IOException {
        //в методе внутри есть оболочка this.utilIOFile.getListStringsFromFile();
        //как ее в тесте мокируют? я же не создам в этом тест-классе поле  @Mock utilIOFile
        //assert(
    }
}

