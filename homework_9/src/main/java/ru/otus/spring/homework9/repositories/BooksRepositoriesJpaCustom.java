package ru.otus.spring.homework9.repositories;

public interface BooksRepositoriesJpaCustom {
    void updateBookStatus(long id, int status);
}
