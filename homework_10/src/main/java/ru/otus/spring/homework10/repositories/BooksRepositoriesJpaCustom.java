package ru.otus.spring.homework10.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepositoriesJpaCustom {
    void updateBookStatus(long id, int status);
}
