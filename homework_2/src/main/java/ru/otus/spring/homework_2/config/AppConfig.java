package ru.otus.spring.homework_2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.homework_2.dao.IOFile;
import ru.otus.spring.homework_2.dao.IOFileImplGetData;
import ru.otus.spring.homework_2.dao.TestDao;
import ru.otus.spring.homework_2.dao.TestDaoImpl;
import ru.otus.spring.homework_2.service.*;


@PropertySource("classpath:settings")
@Configuration
public class AppConfig {

    @Bean
    public IOFile ioFile(@Value("${settings.fileQuestion}") String file) {
        return new IOFileImplGetData(file);
    }

    @Bean
    public TestDao testDao(IOFile IOFileImplGetData, @Value("${settings.countMustRightAnswer}") int countMustRightAnswer) {
        return new TestDaoImpl(IOFileImplGetData, countMustRightAnswer);
    }

    @Bean
    public TestService testService(TestDao testDao, @Value("${settings.questionName1}") String questionFirstName, @Value("${settings.questionName2}") String questionLastName, @Value("${settings.questionName3}") String questionMiddleName) {
        return new TestServiceImpl(testDao, questionFirstName, questionLastName, questionMiddleName);
    }

    @Bean
    public TestScanner testScanner() {
        return new TestScanner();
    }
}
