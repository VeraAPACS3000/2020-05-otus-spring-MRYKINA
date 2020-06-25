package ru.otus.spring.homework_4.dao;

import ru.otus.spring.homework_4.domain.Quiz;

import java.io.IOException;
import java.util.List;

public interface QuizDao {
    List<Quiz> getContainerWithQuiz() throws IOException;

    String resultAnswerPerson(List<Quiz> listObQuiz);
}
