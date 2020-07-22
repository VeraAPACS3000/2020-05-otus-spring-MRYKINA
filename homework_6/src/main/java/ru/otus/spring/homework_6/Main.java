package ru.otus.spring.homework_6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //SpringApplication.run(Main.class);

        ApplicationContext applicationContext = SpringApplication.run(Main.class);

        /*LibraryService libraryService = applicationContext.getBean(LibraryService.class);

        libraryService.getAllBooks();

        libraryService.deleteBookById(1);

        libraryService.getAllBooks();

        libraryService.getAuthorByName("");

        libraryService.findCommentsByNameBook("To Chaadaev");*/
    }

}
