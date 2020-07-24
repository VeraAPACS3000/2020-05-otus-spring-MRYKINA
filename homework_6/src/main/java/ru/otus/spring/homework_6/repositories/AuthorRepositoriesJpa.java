package ru.otus.spring.homework_6.repositories;

import ru.otus.spring.homework_6.models.Author;

public interface AuthorRepositoriesJpa {
    Author findAuthorByName(String name);
}
