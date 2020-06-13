package ru.otus.spring.homework_2.domain;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private int numberQuestion;
    private String textQuestion;
    private int numberRightAnswer;
    private int numberAnswerPerson;
    private List<String> listVariantsAnswer = null;

    public Test() {
        listVariantsAnswer = new ArrayList<>();
    }

    public Test(int numberAnswerPerson, String textQuestion, int numberQuestion,
                int numberRightAnswer, List<String> listVariantsAnswer) {
        this.numberQuestion = numberQuestion;
        this.textQuestion = textQuestion;
        this.numberRightAnswer = numberRightAnswer;
        this.numberAnswerPerson = numberAnswerPerson;
        this.listVariantsAnswer = listVariantsAnswer;
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
}
