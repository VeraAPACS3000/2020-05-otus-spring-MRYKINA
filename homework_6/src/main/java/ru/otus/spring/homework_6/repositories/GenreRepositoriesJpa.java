package ru.otus.spring.homework_6.repositories;

import ru.otus.spring.homework_6.models.Genre;

public interface GenreRepositoriesJpa {
    Genre findGenreByName(String name);
}
