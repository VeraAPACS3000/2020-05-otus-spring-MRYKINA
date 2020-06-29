package ru.otus.spring.homework_4.service;

import java.util.List;

public interface QuizScanner {
    public int runScannerAnswerPerson(String question, List<String> listVariantAnswers);

    public void runScannerFioPerson(String question);
}
