package ru.otus.spring.homework_2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.homework_2.dao.IOFileImplGetData;
import ru.otus.spring.homework_2.dao.ITestDao;
import ru.otus.spring.homework_2.dao.TestDaoImpl;
import ru.otus.spring.homework_2.service.TestScanner;
import ru.otus.spring.homework_2.service.ITestService;
import ru.otus.spring.homework_2.service.TestServiceImpl;


@PropertySource("classpath:settings")
@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public ITestDao testDao(IOFileImplGetData ioFile, @Value("${settings.countMustRightAnswer}") int countMustRightAnswer) {
        return new TestDaoImpl(ioFile, countMustRightAnswer);
    }

    @Bean
    public ITestService testService(ITestDao testDao, TestScanner testScanner, @Value("${settings.questionName1}") String questionFirstName, @Value("${settings.questionName2}") String questionLastName, @Value("${settings.questionName3}") String questionMiddleName) {
        return new TestServiceImpl(testDao, testScanner, questionFirstName, questionLastName, questionMiddleName);
    }
}