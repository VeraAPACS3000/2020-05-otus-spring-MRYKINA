package ru.otus.spring.homework_3.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationService {

    private MessageSource messageSource;

    public LocalizationService(MessageSource inMessageSource) {
        this.messageSource = inMessageSource;
    }

    public String getLocaleFileQuestionForQuiz() {
        return messageSource.getMessage("testing.file", null, Locale.getDefault());
    }

    public String getLocaleFileQuestionPerson() {
        return messageSource.getMessage("user.file", null, Locale.getDefault());
    }

    public String getLocaleResultSuccess() {
        return messageSource.getMessage("testing.result.success", null, Locale.getDefault());
    }

    public String getLocaleResultFailed() {
        return messageSource.getMessage("testing.result.failed", null, Locale.getDefault());
    }
}
