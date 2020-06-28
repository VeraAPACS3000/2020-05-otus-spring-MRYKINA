package ru.otus.spring.homework_4.config;

/*
   Отдельный класс чтобы выгрузить свойства и создав обьект этого класса
   смогу использовать свойства
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application") //связывает свойства с классом свойств
public class YamlPropsSettings {
    private int countMustRightAnswer;
    private Locale locale;

    public int getCountMustRightAnswer() {
        return this.countMustRightAnswer;
    }

    public void setCountMustRightAnswer(int inCountMustRightAnswer) {
        this.countMustRightAnswer = inCountMustRightAnswer;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale inLocale) {
        this.locale = inLocale;
        Locale.setDefault(this.locale);
    }
}
