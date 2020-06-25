package ru.otus.spring.homework_4.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework_4.config.YamlPropsSettings;
import ru.otus.spring.homework_4.domain.Quiz;
import ru.otus.spring.homework_4.service.LocalizationService;

import java.util.ArrayList;
import java.util.List;

import static ru.otus.spring.homework_4.utils.Constants.*;

@Component
public class QuizDaoImpl implements QuizDao {
    private final IOFile utilIOFile;
    private final YamlPropsSettings yamlPropsSettings;
    private final LocalizationService localizationService;

    public QuizDaoImpl(IOFile utilIOFile, YamlPropsSettings yamlPropsSettings, LocalizationService localizationService) {
        this.utilIOFile = utilIOFile;
        this.yamlPropsSettings = yamlPropsSettings;
        this.localizationService = localizationService;
    }

    @Override
    public List<Quiz> getContainerWithQuiz() {
        List<Quiz> listObQuiz = null;
        try {
            List<String> list = this.utilIOFile.getListQuestionTesting();
            listObQuiz = new ArrayList<>();
            Quiz quiz = new Quiz();
            int nextString = 1;
            int numberQuestion = 0;
            for (int i = 0; i < list.size(); i++) {
                String string = list.get(i);
                if (string.equals(HEAD_QUESTION_FROM_FILE)) {
                    quiz.setTextQuestion(list.get(i + nextString));
                } else if (string.equals(HEAD_ANSWER_FROM_FILE)) {
                    quiz.setListVariantsAnswer(list.get(i + nextString));
                } else if (string.equals(HEAD_TRUEANSWER_FROM_FILE)) {
                    quiz.setNumberRightAnswer(Integer.parseInt(list.get(i + nextString)));
                } else if (string.equals(HEAD_END_BLOCK_QUESTION_FROM_FILE)) {
                    ++numberQuestion;
                    quiz.setNumberQuestion(numberQuestion);
                    listObQuiz.add(quiz);
                    quiz = new Quiz();
                }
            }
        } catch (Exception e) {
            throw new GetContainerException(e);
        }
        return listObQuiz;
    }

    @Override
    public String resultAnswerPerson(List<Quiz> listObQuiz) {
        int countRightAnswers = calculateCountRightAnswers(listObQuiz);
        String resultMessage = checkCountRightAnswers(countRightAnswers, yamlPropsSettings.getCountMustRightAnswer());
        return resultMessage;
    }

    private String checkCountRightAnswers(int countRightAnswers, int countMustRightAnswer) {
        String resultMessage = this.localizationService.getLocaleResultFailed();
        if (countRightAnswers >= yamlPropsSettings.getCountMustRightAnswer()) {
            resultMessage = this.localizationService.getLocaleResultSuccess();
        }
        return resultMessage;
    }

    private int calculateCountRightAnswers(List<Quiz> listObQuiz) {
        Quiz quiz = null;
        int countRightAnswers = 0;
        for (int i = 0; i < listObQuiz.size(); i++) {
            quiz = listObQuiz.get(i);
            int numberRight = quiz.getNumberRightAnswer();
            int numberAnswerPerson = quiz.getNumberAnswerPerson();
            if (numberRight == numberAnswerPerson) {
                countRightAnswers++;
            }
        }

        return countRightAnswers;
    }

}
