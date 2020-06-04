package ru.otus.spring.homework_1.domain;

import java.util.Map;

public class TestQuestionAnswer {

    private String textQuestion;
    private Map<Integer, String> mapAnswers;

    public TestQuestionAnswer() {

    }

    public TestQuestionAnswer(String inTextQuestion, Map<Integer, String> inMapAnswers) {
        this.textQuestion = inTextQuestion;
        this.mapAnswers = inMapAnswers;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public Map<Integer, String> getMapAnswers() {
        return this.mapAnswers;
    }

    public void setMapAnswers(Map<Integer, String> mapAnswer) {
        this.mapAnswers = mapAnswer;
    }
}
