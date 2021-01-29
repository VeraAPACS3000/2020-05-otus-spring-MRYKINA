package ru.otus.spring.booksServiceForFeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BooksServiceForFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksServiceForFeignApplication.class, args);
	}

}
