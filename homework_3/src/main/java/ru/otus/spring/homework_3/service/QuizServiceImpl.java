package ru.otus.spring.homework_3.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.homework_3.dao.IOFile;
import ru.otus.spring.homework_3.dao.QuizDao;
import ru.otus.spring.homework_3.domain.Quiz;

import java.util.List;

@Component
public class QuizServiceImpl implements QuizService {

    private final QuizDao quizDao;
    private final QuizScanner quizScanner;
    private final LocalizationService localizationService;
    private final IOFile ioFile;

    public QuizServiceImpl(QuizDao inQuizDao, QuizScanner quizScanner, LocalizationService localizationService, IOFile ioFile) {
        this.quizDao = inQuizDao;
        this.quizScanner = quizScanner;
        this.localizationService = localizationService;
        this.ioFile = ioFile;
    }

    @Override
    public String runQuizPerson() {
        String result = "";
        try {
            printsQuestionPersonalData();
            List<Quiz> listObQuiz = this.quizDao.getContainerWithQuiz();
            Quiz quiz = null;
            for (int i = 0; i < listObQuiz.size(); i++) {
                quiz = listObQuiz.get(i);
                int numberAnswerPerson =
                        quizScanner.runScannerAnswerPerson(quiz.getTextQuestion(), quiz.getListVariantsAnswer());
                quiz.setNumberAnswerPerson(numberAnswerPerson);
            }
            result = quizDao.resultAnswerPerson(listObQuiz);
            System.out.println(result);

        } catch (Exception e) {
            throw new RunServiceException(e);
        }
        return result;
    }

    private void printsQuestionPersonalData() {
        List<String> listQuestionsPerson = this.ioFile.getListQuestionPersonData();
        for (int i = 0; i < listQuestionsPerson.size(); i++) {
            quizScanner.runScannerFioPerson(listQuestionsPerson.get(i));
        }
    }
}
