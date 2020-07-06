package ru.otus.spring.homework_5.dao;

import ru.otus.spring.homework_5.domain.Books;

import java.util.List;

public interface BookDao {
    List<Books> getAll();

    Books findById(long id);

    int insert(Books books);

    int updateByStatus(long id, int status);

    int deleteById(long id);
}
