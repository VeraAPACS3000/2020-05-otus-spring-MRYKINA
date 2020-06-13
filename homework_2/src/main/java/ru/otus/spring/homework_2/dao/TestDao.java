package ru.otus.spring.homework_2.dao;

import ru.otus.spring.homework_2.domain.Test;

import java.io.IOException;
import java.util.List;

public interface TestDao {
    List<Test> getContainerWithTests() throws IOException;

    String resultAnswerPerson(List<Test> listObTest);
}
