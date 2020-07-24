package ru.otus.spring.homework_6.services;

import ru.otus.spring.homework_6.models.Author;

public interface AuthorService {

    Author getAuthorByName(String nameAuthor);

}
