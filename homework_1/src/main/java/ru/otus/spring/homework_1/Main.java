package ru.otus.spring.homework_1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.homework_1.Utils.Utils;
import ru.otus.spring.homework_1.domain.TestQuestionAnswer;
import ru.otus.spring.homework_1.resource.ResourceMap;
import ru.otus.spring.homework_1.resource.Resources;
import ru.otus.spring.homework_1.service.TestService;
import ru.otus.spring.homework_1.service.TestServiceImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        Resources resourceMap = context.getBean(ResourceMap.class);
        Map<Map<String, Integer>, Map<Integer, String>> mapTest = resourceMap.readerResource();
        TestService testService = context.getBean(TestServiceImpl.class);
        printTest(mapTest, testService);
    }

    private static void printTest(Map<Map<String, Integer>, Map<Integer, String>> mapTest, TestService testService) {
        Collection<Map<Integer, String>> mapQuestionCollection = mapTest.values();
        Map<String, Integer> mapAnswer = Utils.getMapAnswerFromInput(mapTest, mapQuestionCollection);

        for (Map<Integer, String> mapQuestion : mapQuestionCollection) {
            for (int i = 0; i < mapQuestion.size(); i++) {
                TestQuestionAnswer test = testService.getTestWithQuestionANdAnswer(mapQuestion, mapAnswer, i);
                System.out.println(i + ". "+ test.getTextQuestion());
                System.out.println(test.getMapAnswers());
                System.out.println("--------------------------------------------------");
            }
        }
    }
}