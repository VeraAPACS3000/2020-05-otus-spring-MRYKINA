package ru.otus.spring.homework_2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.homework_2.Utils.TestScanner;
import ru.otus.spring.homework_2.Utils.UtilIOFile;
import ru.otus.spring.homework_2.dao.TestDao;
import ru.otus.spring.homework_2.dao.TestDaoImpl;
import ru.otus.spring.homework_2.service.ManagerAuthenticationUser;
import ru.otus.spring.homework_2.service.ManagerAuthenticationUserImpl;
import ru.otus.spring.homework_2.service.TestService;
import ru.otus.spring.homework_2.service.TestServiceImpl;

@PropertySource("classpath:settings")
@Configuration
public class AppConfig {

    @Bean
    public ManagerAuthenticationUser managerShowQuestion() {
        return new ManagerAuthenticationUserImpl();
    }

    @Bean
    public UtilIOFile utilIOFile(@Value("${settings.fileQuestion}") String file) {
        return new UtilIOFile(file);
    }

    @Bean
    public TestDao testDao(UtilIOFile utilIOFile, @Value("${settings.countMustRightAnswer}") int countMustRightAnswer){
        return new TestDaoImpl(utilIOFile, countMustRightAnswer);
    }

    @Bean
    public TestService testService(TestDao testDao){
        return new TestServiceImpl(testDao);
    }

    @Bean
    public TestScanner testScanner(){
        return new TestScanner();
    }
}
