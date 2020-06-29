package ru.otus.spring.homework_4.domain;

import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private final List<String> listVariantsAnswer = new ArrayList<>();
    private int numberQuestion;
    private String textQuestion;
    private int numberRightAnswer;
    private int numberAnswerPerson;

    public Quiz() {

    }

    public void setNumberAnswerPerson(int inNumberAnswerPerson) {
        this.numberAnswerPerson = inNumberAnswerPerson;
    }

    public void setListVariantsAnswer(String variantAnswer) {
        this.listVariantsAnswer.add(variantAnswer);
    }

    public void setNumberQuestion(int inNumberQuestion) {
        this.numberQuestion = inNumberQuestion;
    }

    public void setNumberRightAnswer(int inNumberRightAnswer) {
        this.numberRightAnswer = inNumberRightAnswer;
    }

    public void setTextQuestion(String inTextQuestion) {
        this.textQuestion = inTextQuestion;
    }

    public List<String> getListVariantsAnswer() {
        return listVariantsAnswer;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public int getNumberRightAnswer() {
        return numberRightAnswer;
    }

    public int getNumberAnswerPerson() {
        return numberAnswerPerson;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }
}
