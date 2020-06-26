package ru.otus.spring.homework_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.homework_3.config.YamlPropsSettings;
import ru.otus.spring.homework_3.service.QuizService;

import java.io.IOException;

/*@SpringBootApplication Содержит
@Configuration
@EnableAutoConfiguration
@ComponentScan
*/

@SpringBootApplication()
@EnableConfigurationProperties(YamlPropsSettings.class)
public class Main {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        context.getBean(QuizService.class).runQuizPerson();
    }
}