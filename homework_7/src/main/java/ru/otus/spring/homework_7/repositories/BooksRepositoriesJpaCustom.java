package ru.otus.spring.homework_7.repositories;

public interface BooksRepositoriesJpaCustom {
    void updateBookStatus(long id, int status);
}
