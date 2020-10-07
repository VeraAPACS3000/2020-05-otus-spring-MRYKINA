package ru.otus.spring.homework11;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.config.EnableWebFlux;


@EnableWebFlux
@EnableMongock
@SpringBootApplication
//@EnableConfigurationProperties
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
