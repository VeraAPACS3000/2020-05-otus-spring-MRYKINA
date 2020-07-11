package ru.otus.spring.homework_5.service;

import ru.otus.spring.homework_5.domain.Books;

import java.util.List;

public interface BooksService {

    List<Books> getAllBooks();

    Books findBookById(long id);

    void deleteBookById(long id);

    void updateStatusBook(long id, int status);

    void insertNewBook(Books books);

}
