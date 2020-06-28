package ru.otus.spring.homework_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.otus.spring.homework_4.config.YamlPropsSettings;

@SpringBootApplication()
@EnableConfigurationProperties(YamlPropsSettings.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}