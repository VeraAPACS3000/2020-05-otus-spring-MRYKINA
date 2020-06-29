package ru.otus.spring.homework_4.dao;

import org.springframework.stereotype.Service;
import ru.otus.spring.homework_4.service.LocalizationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class IOFileImplGetData implements IOFile {

    private final LocalizationService localizationService;

    public IOFileImplGetData(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @Override
    public List<String> getListQuestionTesting() {
        List<String> listResult = null;
        try {
            listResult = readFromFile(loadFileQuestionsTesting());
        } catch (Exception e) {
            throw new GetDataException("Error while get data list question quiz", e);
        }
        return listResult;
    }

    @Override
    public List<String> getListQuestionPersonData() {
        List<String> listResult = null;
        try {
            listResult = readFromFile(loadFileQuestionsPersonData());
        } catch (Exception e) {
            throw new GetDataException("Error while get data list question person", e);
        }
        return listResult;
    }

    private List<String> readFromFile(File file) {
        List<String> listResultStrings = new ArrayList<>();
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    listResultStrings.add(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                throw new QuizFileNotFoundException("Error while scanner file file.getName()", e);
            }
        }
        return listResultStrings;
    }

    private File loadFileQuestionsTesting() {
        return new File(getClass().getClassLoader().getResource(this.localizationService.getLocaleFileQuestionForQuiz()).getFile());
    }

    private File loadFileQuestionsPersonData() {
        return new File(getClass().getClassLoader().getResource(this.localizationService.getLocaleFileQuestionPerson()).getFile());
    }

}
