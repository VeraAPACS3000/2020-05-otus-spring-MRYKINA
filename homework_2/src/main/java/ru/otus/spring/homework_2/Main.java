package ru.otus.spring.homework_2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.homework_2.service.ITestService;

import java.io.IOException;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) throws IOException {

        AnnotationConfigApplicationContext annotationConfig =
                new AnnotationConfigApplicationContext(Main.class);

        annotationConfig.getBean(ITestService.class).runTestingPerson();
    }
}