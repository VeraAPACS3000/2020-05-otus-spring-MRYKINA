package ru.otus.spring.homework7.repositories;

public interface BooksRepositoriesJpaCustom {
    void updateBookStatus(long id, int status);
}
