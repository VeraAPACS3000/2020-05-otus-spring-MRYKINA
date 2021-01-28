package ru.otus.spring.homework12.repositories;

import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepositoriesJpaCustom {
    void updateBookStatus(long id, int status);
}
