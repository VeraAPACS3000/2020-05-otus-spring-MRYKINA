package ru.otus.spring.homework_6.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_6.models.Author;
import ru.otus.spring.homework_6.repositories.AuthorRepositoriesJpa;

@Service
public class AuthorServiceImpl implements AuthorService {

    AuthorRepositoriesJpa repositories;

    public AuthorServiceImpl(AuthorRepositoriesJpa repositories) {
        this.repositories = repositories;
    }


    @Transactional
    @Override
    public Author getAuthorByName(String nameAuthor) {
        Author author = null;
        if (repositories.findAuthorByName(nameAuthor) != null) {
            author = repositories.findAuthorByName(nameAuthor);
            System.out.println(author.toString());
        } else {
            System.out.println("not found the author");
        }
        return author;
    }
}
